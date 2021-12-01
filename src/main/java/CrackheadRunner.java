class CrackheadRunner{
    public static void main(String[] args){

        benchmarkCracker(1,9);
        benchmarkCracker(2,21);
        benchmarkCracker(3,537);
        benchmarkCracker(4,4683);
        benchmarkCracker(5,34679);
        benchmarkCracker(6,824737);
    }
    static void benchmarkCracker(int len, int target){
        PinCracker cracker = new PinCracker(len,HashingAlg.encryptHash(String.valueOf(target)));
        long st = System.currentTimeMillis();
        String result = cracker.seek();
        long en = System.currentTimeMillis();
        System.out.println(result + " in " + (en-st) + " ms");
    }
}
