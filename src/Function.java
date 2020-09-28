public class Function {
    public static double sinus(int x){
        double accuracy = Math.pow(10,-6);
        double rad = 0;
        double result = 0;
        double delta = 1;
        int sign = 1;
        int i=1;

        if (x >= 0){
            if (x%90==0 && (x/90)%2==1){
                if ((x/90)%4 == 1) return 1.0;
                else return -1.0;
            } else if (x%90==0 && x/90%2==0) return 0.0;
            else {

            int n = (x / 90) % 4;
            int k = x % 90;
            switch (n){
                case 0:
                    rad = 0.0175*k;
                    break;
                case 1:
                    rad = 0.0175*(90-k);
                    break;
                case 2:
                    sign = -1;
                    rad = 0.0175*(k);
                    break;
                case 3:
                    sign = -1;
                    rad = 0.0175*(90-k);
                    break;
                }
            }
        }
        else {
            if (x%90==0 && (x/90)%2==-1){
                if ((x/90)%4 == -1) return -1.0;
                else return 1.0;
            } else if (x%90==0 && x/90%2==0) return 0.0;
            else {
            int n = ((-1)*x / 90) % 4;
            int k = (-1)*x % 90;
            switch (n){
                case 3:
                    rad = 0.0175*(90-k);
                    break;
                case 2:
                    rad = 0.0175*k;
                    break;
                case 1:
                    sign = -1;
                    rad = 0.0175*(90-k);
                    break;
                case 0:
                    sign = -1;
                    rad = 0.0175*(k);
                    break;
            }
        }}
        while (Math.abs(delta)  > accuracy) {
            delta = Math.pow(rad, i) * getSign(i) / getFactorial(i);
            result += delta;
            i += 2;
        }
        //result += delta;
        return result*sign;
    }
    public static int getSign(int f) {
        int result = 1;
        if (f == 0) result = 1;
        else if ((f/2)%2 == 1) result=-1;
        return result;
    }
    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}
