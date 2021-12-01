class PinCracker{
    private final static String [] ZEROES = new String[]{"","0","00","000","0000","00000","000000","0000000","00000000"};
    private final int length;
    private final String target;
    protected PinCracker(int length, String target){
        this.length = length;
        this.target = target;
    }

    protected String seek(){
        final int TIMES = (int) Math.pow(10,length);
        int num = 0;
        for(int i = 0; i<TIMES; i++){
            if(HashingAlg.encryptHash(pad(num)).equals(target)){
                return pad(num);
            }
            num++;
        }
        return "Not found";
    }
    private String pad(int num){
        int toPad = length - getNumLength(num);
        if(num == 0) return ZEROES[toPad];
        return ZEROES[toPad] + num;
    }

    private int getNumLength(int num){
        int len = 0;
        while(num!=0){
            num = num/10;
            len++;
        }
        return len;
    }
}

