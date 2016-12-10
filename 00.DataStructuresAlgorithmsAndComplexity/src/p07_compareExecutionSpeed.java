public class p07_compareExecutionSpeed {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            isPrimeFast(i);
        }
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }

    private static boolean isPrime(long num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean isPrimeFast(long num) {
        int maxDivisor = (int)Math.sqrt(num);

        for (int i = 2; i < maxDivisor; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
