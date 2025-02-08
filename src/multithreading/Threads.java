package multithreading;

public class Threads {

    public static void main(String[] args) {

        /**
         * Ways to create and execute threads
         * 
         * Method 1 : Create an Extension class of the Java Thread Class
         * Method 2 : Create an Implementation class of the Java Runnable Interface
         * Method 3 : Create an Anonymous inner class to implement Runnable Interface
         * Method 4 : Create an implementation of the Runnable interface using Lambda
         * Expressions
         */

        // Executing Method : ThreadExtension
        final ThreadExtension extension = new ThreadExtension();
        extension.start();

        // Executing Method : RunnableImplementation
        Thread runnableThread = new Thread(new RunnableImplementation());
        runnableThread.start();

        // Executing Method : AnonymousRunnableImplementation
        final Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing AnonymousRunnableImplementation");
                System.out.println("Finished AnonymousRunnableImplementation");
            }
        };
        final Thread anonymousRunnableThread = new Thread(anonymousRunnable);
        anonymousRunnableThread.start();

        // Executing Method : LambdaRunnable
        final Runnable lambdaRunnable = () -> {
            System.out.println("Executing LambdaRunnable");
            System.out.println("Finished LambdaRunnable");
        };

        Thread lambdaRunnableThread = new Thread(lambdaRunnable);
        lambdaRunnableThread.start();
    }
}

class ThreadExtension extends Thread {
    @Override
    public void run() {
        System.out.println("Executing ThreadExtension");
        System.out.println("Finished ThreadExtension");
    }
}

class RunnableImplementation implements Runnable {

    @Override
    public void run() {
        System.out.println("Executing RunnableImplementation");
        System.out.println("Finished RunnableImplementation");
    }

}
