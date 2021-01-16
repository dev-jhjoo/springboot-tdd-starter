package dev.jh.joo.junit5;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;


//JUnit5 확장모델
class ExtensionAppTest implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final long THRESHOLD = 1000L;

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method requiredTestMethod = context.getRequiredTestMethod();
        CustomAnnotationTest annotation = requiredTestMethod.getAnnotation(CustomAnnotationTest.class);

        String methodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = getStore(context);

        long start_time = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;

        // 1s 보다 긴 테스트에 경우 해당 msg 출력
        if(duration > THRESHOLD && annotation == null) {
            System.out.printf("[WRAN]Please consider mark method [%s] with @CustomAnnotationTest.\n", methodName);
        }
    }

    private ExtensionContext.Store getStore(ExtensionContext context){
        String className = context.getRequiredTestClass().getName();
        String methodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(
                className, methodName
        ));
    }
}