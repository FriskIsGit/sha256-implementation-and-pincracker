import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @SHA-256
 *
 */
public class HashingAlg {
    final static BigInteger[] BIG_HASHES_8 = new BigInteger[]{
            new BigInteger("1779033703"),
            new BigInteger("3144134277"),
            new BigInteger("1013904242"),
            new BigInteger("2773480762"),
            new BigInteger("1359893119"),
            new BigInteger("2600822924"),
            new BigInteger("528734635"),
            new BigInteger("1541459225")};
    final static BigInteger [] BIG_HASHES_64 = new BigInteger[]{new BigInteger("1116352408"),
            new BigInteger("1899447441"),
            new BigInteger("3049323471"),
            new BigInteger("3921009573"),
            new BigInteger("961987163"),
            new BigInteger("1508970993"),
            new BigInteger("2453635748"),
            new BigInteger("2870763221"),
            new BigInteger("3624381080"),
            new BigInteger("310598401"),
            new BigInteger("607225278"),
            new BigInteger("1426881987"),
            new BigInteger("1925078388"),
            new BigInteger("2162078206"),
            new BigInteger("2614888103"),
            new BigInteger("3248222580"),
            new BigInteger("3835390401"),
            new BigInteger("4022224774"),
            new BigInteger("264347078"),
            new BigInteger("604807628"),
            new BigInteger("770255983"),
            new BigInteger("1249150122"),
            new BigInteger("1555081692"),
            new BigInteger("1996064986"),
            new BigInteger("2554220882"),
            new BigInteger("2821834349"),
            new BigInteger("2952996808"),
            new BigInteger("3210313671"),
            new BigInteger("3336571891"),
            new BigInteger("3584528711"),
            new BigInteger("113926993"),
            new BigInteger("338241895"),
            new BigInteger("666307205"),
            new BigInteger("773529912"),
            new BigInteger("1294757372"),
            new BigInteger("1396182291"),
            new BigInteger("1695183700"),
            new BigInteger("1986661051"),
            new BigInteger("2177026350"),
            new BigInteger("2456956037"),
            new BigInteger("2730485921"),
            new BigInteger("2820302411"),
            new BigInteger("3259730800"),
            new BigInteger("3345764771"),
            new BigInteger("3516065817"),
            new BigInteger("3600352804"),
            new BigInteger("4094571909"),
            new BigInteger("275423344"),
            new BigInteger("430227734"),
            new BigInteger("506948616"),
            new BigInteger("659060556"),
            new BigInteger("883997877"),
            new BigInteger("958139571"),
            new BigInteger("1322822218"),
            new BigInteger("1537002063"),
            new BigInteger("1747873779"),
            new BigInteger("1955562222"),
            new BigInteger("2024104815"),
            new BigInteger("2227730452"),
            new BigInteger("2361852424"),
            new BigInteger("2428436474"),
            new BigInteger("2756734187"),
            new BigInteger("3204031479"),
            new BigInteger("3329325298"),
    };
    final static String[] HASHES_8 = {"6a09e667", "bb67ae85", "3c6ef372", "a54ff53a", "510e527f", "9b05688c", "1f83d9ab", "5be0cd19"},
            HASHES_64 = {
                    "428a2f98",
                    "71374491",
                    "b5c0fbcf",
                    "e9b5dba5",
                    "3956c25b",
                    "59f111f1",
                    "923f82a4",
                    "ab1c5ed5",
                    "d807aa98",
                    "12835b01",
                    "243185be",
                    "550c7dc3",
                    "72be5d74",
                    "80deb1fe",
                    "9bdc06a7",
                    "c19bf174",
                    "e49b69c1",
                    "efbe4786",
                    "0fc19dc6",
                    "240ca1cc",
                    "2de92c6f",
                    "4a7484aa",
                    "5cb0a9dc",
                    "76f988da",
                    "983e5152",
                    "a831c66d",
                    "b00327c8",
                    "bf597fc7",
                    "c6e00bf3",
                    "d5a79147",
                    "06ca6351",
                    "14292967",
                    "27b70a85",
                    "2e1b2138",
                    "4d2c6dfc",
                    "53380d13",
                    "650a7354",
                    "766a0abb",
                    "81c2c92e",
                    "92722c85",
                    "a2bfe8a1",
                    "a81a664b",
                    "c24b8b70",
                    "c76c51a3",
                    "d192e819",
                    "d6990624",
                    "f40e3585",
                    "106aa070",
                    "19a4c116",
                    "1e376c08",
                    "2748774c",
                    "34b0bcb5",
                    "391c0cb3",
                    "4ed8aa4a",
                    "5b9cca4f",
                    "682e6ff3",
                    "748f82ee",
                    "78a5636f",
                    "84c87814",
                    "8cc70208",
                    "90befffa",
                    "a4506ceb",
                    "bef9a3f7",
                    "c67178f2",
    };
    public static void main(String[] args) {
        //String str = "hello world"; //b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9
        String str = "password12345678";
        System.out.println(encryptHash(str));

    }
    protected static String encryptHash(String str){
        //STEP_2,3 initialize hashes h0-h7

        ArrayList<String> bitList = new ArrayList<>();
        int messageLength = str.length(), kBytesToPad;
        //STEP_1
        for(int i =0; i<str.length();i++){
            StringBuilder currentString = new StringBuilder(getBinary(str.charAt(i)));
            for(int j = currentString.length();j<8;j++){
                currentString.insert(0, "0");
            }
            bitList.add(currentString.toString());
        }
        //00111001
        bitList.add("10000000");
        //STEP_4 chunk looping
        if(messageLength+9<=64){
            kBytesToPad=64-(messageLength+9);
        }else{
            kBytesToPad=(((messageLength+9)/64)+1)*64 - (messageLength+9);
        }
        //padding
        fillData(kBytesToPad,bitList);

        StringBuilder strBinary = new StringBuilder(getBinary(messageLength*8));
        while(strBinary.length()<64){
            strBinary.insert(0,'0');
        }

        for(int chunks = 0; chunks<=56;chunks=chunks+8){
            bitList.add(strBinary.substring(chunks,chunks+8));
        }
        //streamBitList(bitList);
        //STEP_5 turn each entry into 32-bit words
        BigInteger [] w = new BigInteger[64];
        //System.out.println("size of bitlist: " + bitList.size());

        for(int elArr = 0, elList=0; elList<=bitList.size()-4;elArr++,elList=elList+4){
            w[elArr]= new BigInteger(bitList.get(elList)+bitList.get(elList+1)+bitList.get(elList+2)+bitList.get(elList+3),2);
        }
        //fill up with 0's
        fillUpBigIntArr(w);

        //stream array
        //streamArray(w);
        //modify array
        BigInteger maxU32 = new BigInteger("4294967295");
        for(int i = 16; i<w.length;i++){
            BigInteger s0 = rotate_Right(w[i-15],7).xor(rotate_Right(w[i-15],18)).xor(w[i-15].shiftRight(3));
            BigInteger s1 = rotate_Right(w[i-2],17).xor(rotate_Right(w[i-2],19)).xor(w[i-2].shiftRight(10));
            w[i] = (w[i-16].add(s0).and(maxU32).add(w[i-7]).and(maxU32).add(s1).and(maxU32));
            //streamArray(w);
        }
        //streamArray(w);
        //STEP_6 compression
        //hashes without prefix
        BigInteger a = BIG_HASHES_8[0];
        BigInteger b = BIG_HASHES_8[1];
        BigInteger c = BIG_HASHES_8[2];
        BigInteger d = BIG_HASHES_8[3];
        BigInteger e = BIG_HASHES_8[4];
        BigInteger f = BIG_HASHES_8[5];
        BigInteger g = BIG_HASHES_8[6];
        BigInteger h = BIG_HASHES_8[7];

        for (int i = 0; i<64;i++) {
            BigInteger S1 = rotate_Right(e,6).xor(rotate_Right(e,11)).xor(rotate_Right(e,25));
            //bitflip for ~e
            //BigInteger flippedE = flipBits(e);
            BigInteger ch = (e.and(f)).xor((bit_not_string(e)).and(g));
            BigInteger temp1 = h.add(S1).and(maxU32).add(ch).and(maxU32).add(BIG_HASHES_64[i]).and(maxU32).add(w[i]).and(maxU32);
            BigInteger S0 = rotate_Right(a,2).xor(rotate_Right(a,13)).xor(rotate_Right(a,22));
            BigInteger maj = (a.and(b)).xor(a.and(c)).xor(b.and(c));
            BigInteger temp2 = S0.add(maj).and(maxU32);
            h = g;
            g = f;
            f = e;
            e = d.add(temp1).and(maxU32);
            d = c;
            c = b;
            b = a;
            a = temp1.add(temp2).and(maxU32);
        }

        HASHES_8[0] = new BigInteger(String.valueOf(BIG_HASHES_8[0].add(a))).and(maxU32).toString(16);
        HASHES_8[1] = new BigInteger(String.valueOf(BIG_HASHES_8[1].add(b))).and(maxU32).toString(16);
        HASHES_8[2] = new BigInteger(String.valueOf(BIG_HASHES_8[2].add(c))).and(maxU32).toString(16);
        HASHES_8[3] = new BigInteger(String.valueOf(BIG_HASHES_8[3].add(d))).and(maxU32).toString(16);
        HASHES_8[4] = new BigInteger(String.valueOf(BIG_HASHES_8[4].add(e))).and(maxU32).toString(16);
        HASHES_8[5] = new BigInteger(String.valueOf(BIG_HASHES_8[5].add(f))).and(maxU32).toString(16);
        HASHES_8[6] = new BigInteger(String.valueOf(BIG_HASHES_8[6].add(g))).and(maxU32).toString(16);
        HASHES_8[7] = new BigInteger(String.valueOf(BIG_HASHES_8[7].add(h))).and(maxU32).toString(16);

        return HASHES_8[0]+HASHES_8[1]+HASHES_8[2]+HASHES_8[3]+HASHES_8[4]+HASHES_8[5]+HASHES_8[6]+HASHES_8[7];
    }

    private static void fillUpBigIntArr(BigInteger[] w) {
        for(int i = 16; i<w.length;i++){
            if(w[i]==null) w[i] = new BigInteger("0");
        }
    }

    private static BigInteger flipBits(BigInteger e) {
        char [] arr = e.toString(2).toCharArray();
        for(int i = 0; i<arr.length;i++){
            if(arr[i]=='0') arr[i]='1';
            else{
                arr[i]='0';
            }
        }
        return new BigInteger(new String(arr),2);
    }

    private static BigInteger rotateRightBigInt(BigInteger num,int displacement) {
        return (num.shiftRight(displacement)).or(num.shiftLeft (32 - displacement));
    }
    private static long rotateRightLong(long num, long displacement) {
        return (num >>> displacement) | (num << (32 - displacement));
    }
    private static BigInteger rotate_Right(BigInteger num, int displacement){
        // Convert to binary
        // Fill until total number of bits == 32
        // Cut right part of the string that requires a rotation to the other side of the string
        // Append the part that was cut out on the left side of the string
        // return string / convert it to 64 bit int
        String s = num.toString(2);
        StringBuilder num_bin = new StringBuilder();

        while (num_bin.length() + s.length() < 32) {
            num_bin.append('0');
        }
        num_bin.append(s);

        int lengthOfNumBin = num_bin.length();
        String slice = num_bin.substring(lengthOfNumBin-displacement,lengthOfNumBin);

        return new BigInteger(slice + num_bin.substring(0, lengthOfNumBin - displacement),2);
    }
    private static BigInteger bit_not_string(BigInteger num){
        // iterate over all characters and reverse bits
        // append 1's to the front until total length of the string is 32
        // return string

        char [] arr = num.toString(2).toCharArray();
        for(int i = 0; i<arr.length;i++){
            if(arr[i]=='0') arr[i]='1';
            else{
                arr[i]='0';
            }
        }
        StringBuilder slice = new StringBuilder(new String(arr));
        while(slice.length()<32){
            slice.insert(0,'1');
        }
        return new BigInteger(slice.toString(),2);
        // 101
        // 00000000000000000000000000 101
        // 111111111111111111111 010
    }

    private static void streamArray(BigInteger [] arr){
        for(int entry = 0; entry<arr.length;entry++){
            if(entry%2==0) System.out.println();
            System.out.print(arr[entry] + " ");
        }
        System.out.println("\n-----------------------------------------------------------------------");
    }
    private static void streamBitList(ArrayList<String> list){
        for(int i = 0; i<list.size();i++){
            if(i%8==0 && i!=0) System.out.println();
            System.out.print(list.get(i) + " ");
        }
        System.out.println("\n-----------------------------------------------------------------------");
    }
    private static void fillData(int numberOfBytes, ArrayList<String> list){
        for(int i = 0; i<numberOfBytes;i++){
            list.add("00000000");
        }
    }

    private static String getBinary(long number){
        StringBuilder binaryString = new StringBuilder("");
        while(true){
            binaryString.append(number%2);
            if (number/2==0) break;
            number = number/2;
        }
        return binaryString.reverse().toString();
    }


}
