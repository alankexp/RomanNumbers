/**
 * Given a positive integer number (eg 42) determine
 its Roman numeral representation as a String (eg "XLII").

 You cannot write numerals like IM for 999.
 Wikipedia states "Modern Roman numerals are written by
 expressing each digit separately starting with the
 leftmost digit and skipping any digit with a value of zero."

 Examples:

 1 ->    "I" | 10 ->    "X" | 100 ->    "C" | 1000 ->    "M"
 2 ->   "II" | 20 ->   "XX" | 200 ->   "CC" | 2000 ->   "MM"
 3 ->  "III" | 30 ->  "XXX" | 300 ->  "CCC" | 3000 ->  "MMM"
 4 ->   "IV" | 40 ->   "XL" | 400 ->   "CD" | 4000 -> "MMMM"
 5 ->    "V" | 50 ->    "L" | 500 ->    "D" |
 6 ->   "VI" | 60 ->   "LX" | 600 ->   "DC" |
 7 ->  "VII" | 70 ->  "LXX" | 700 ->  "DCC" |
 8 -> "VIII" | 80 -> "LXXX" | 800 -> "DCCC" |
 9 ->   "IX" | 90 ->   "XC" | 900 ->   "CM" |

 1990 -> "MCMXC"  (1000 -> "M"  + 900 -> "CM" + 90 -> "XC")
 2008 -> "MMVIII" (2000 -> "MM" + 8 -> "VIII")
 99 -> "XCIX"   (90 -> "XC" + 9 -> "IX")
 47 -> "XLVII"  (40 -> "XL" + 7 -> "VII")

 * ��ɵĴ�������ʱ�ع���Ľ������һ���������Ƶģ������ѻ����ﵽ�᲻����ζ��
 * ���¸�ϰ��TDD���ܽ�һ�£�
 * a ���Ȼ��ֹ��������������λ������|ʮλ������|��λ������|ǧλ��������
 * b ����򵥵ĸ�λ����������������keep simple, stupid!�ҳ����ֹ��ɣ��ֱ�����������4,5,9����������
 * c ����ʵ��ʮλ��������ʮλ����ҪǶ�׸�λ���ĺ���һ��ʵ��ת������
 * d ����ʵ�ְ�λ�������ְ�λ����ҪǶ��ʮλ���ĺ���һ��ʵ��ת������
 * e ����ʵ��ǧλ��������ǧλ����ҪǶ�װ�λ���ĺ���һ��ʵ��ת�����ܣ������д��ڲ�ͬ������Ҫ��4,5,9��������������
 * f �����������֣���ʹ�ø�λ����Ϊ0�����֣����Զ���ͨ��������4321������ʹ��4021����4001���ԣ�ʧ����
 * g ��ᵽ��Ч������������Ҫ�ԣ���ô��������Ч���ԣ����ֲ����ܱ���5000�Σ�д5000��equals�������ʱû���뷨
 * h ������˲���f���ֵ�������Ѿ���������ʵ������Ŀ���ˣ�����ʹ���������ݹ��Լ������ع�����
 * i �����в�������ͨ��֮��������Ϊ���У����ܲ����������ַ�����ͬ�Ĵ���飬OK��DRP�������ع�
 * j ��������Ҫ4���������ֱ�ʵ�ָ�λ��ʮλ����λ��ǧλ���ֱ��ǣ�
 * convertOneDigitalToRoman|convertTwoDigitalToRoman|convertThreeDigitalToRoman|convertFourDigitalToRoman
 * �ع�֮��ĳ�һ��������
 * convertDigitalToRomanByDigitalLength
 * �ع��Ĺ��̲��Ǻ������ģ�������TDD��֤������һ������������ʵ����Ŀ�ꡣ���վ���4�����������
 * convertDigitalToRoman
 * convertDigitalToRomanByDigitalLength
 * convertSpecialDigit459
 * convertNormalDigitalToRoman
 */
public class RomanNumber {
    String[] smallerThanFive = {"I","X","C","M"};
    String[] largerThanFive =  {"V","L","D"};

    /**
     * ת����ں����������ݹ��߼���
     * @param digit
     * @return
     */
    public String convertDigitalToRoman(int digit){
        String roman = "";
        if (0 < digit && digit < 10){
            roman = convertDigitalToRomanByDigitalLength(digit, 1);
        }
        if (9 < digit && digit < 100){
            roman = convertDigitalToRomanByDigitalLength(digit, 2);
        }
        if(99 < digit && digit < 1000){
            roman = convertDigitalToRomanByDigitalLength(digit, 3);
        }
        if(999 < digit && digit < 5000){
            roman = convertDigitalToRomanByDigitalLength(digit, 4);
        }
        return roman;
    }

    /**
     * �������ֳ���ת���������֣����ϸ�λ��ǧλ����
     * @param digit
     * @param len λ���������λ��1��ʮλ��2����λ��3��ǧλ��4
     * @return
     */
    public String convertDigitalToRomanByDigitalLength(int digit, int len){
        int tenspow = (int)Math.pow(10,len-1);
        int preDigit = digit/tenspow;//��ת�����ֵ����λ
        int nextDigit = digit%tenspow;//��ת�����ֵĴθ�λ
        if (len != 4){//ǧλ������Ҫ����������������
            String x = convertSpecialDigit459(preDigit, nextDigit, len);
            if (x != null)
                return x;
        }
        return convertNormalDigitalToRoman(preDigit, nextDigit, len);
    }

    /**
     * ת�������4,5,9��ͷ������
     * @param preDigit
     * @param nextDigit
     * @param len
     * @return
     */
    private String convertSpecialDigit459(int preDigit,  int nextDigit, int len) {
        if (preDigit == 4){
            return  smallerThanFive[len-1] + largerThanFive[len-1] + convertDigitalToRoman(nextDigit);
        }
        if (preDigit == 5){
            return largerThanFive[len-1] + convertDigitalToRoman(nextDigit);
        }
        if (preDigit == 9){
            return  smallerThanFive[len-1] + smallerThanFive[len] + convertDigitalToRoman(nextDigit);
        }
        return null;
    }

    /**
     * ת������4,5,9���������
     * @param preDigit
     * @param nextDigit
     * @param len
     * @return
     */
    private String convertNormalDigitalToRoman(int preDigit, int nextDigit, int len) {
        String roman = "";
        for (int i = 0; i < ((preDigit-5) > 0 ? preDigit-5: preDigit); i++){
            roman+=smallerThanFive[len-1];
        }
        roman += convertDigitalToRoman(nextDigit);
        if (preDigit > 5 && len != 4){
            return largerThanFive[len-1] + roman;
        }
        return roman;
    }

    /**
     * ת����λ��
     * @param digit
     * @return
     */
    public String convertOneDigitalToRoman(int digit) {
        String x = convertSpecialDigit459(digit, 0, 1);
        if (x != null) {
            return x;
        }
        return convertNormalDigitalToRoman(digit, 0, 1);
    }

    /**
     * ת��ʮλ��
     * @param digit
     * @return
     */
    public String convertTwoDigitalToRoman(int digit) {
        int tenDigit = digit/10;
        int unitDigit = digit%10;
        String x = convertSpecialDigit459(tenDigit,unitDigit, 2);
        if (x != null)
            return x;
        return convertNormalDigitalToRoman(tenDigit, unitDigit, 2);
    }

    /**
     * ת����λ��
     * @param digit
     * @return
     */
    public String convertThreeDigitalToRoman(int digit) {
        String roman = "";
        int hundredDigit = digit/100;
        int tenDigit = digit%100;
        String x = convertSpecialDigit459(hundredDigit,tenDigit, 3);
        if (x != null)
            return x;
        return convertNormalDigitalToRoman(hundredDigit, tenDigit, 3);
    }

    /**
     * ת��ǧλ��
     * @param digit
     * @return
     */
    public String convertFourDigitalToRoman(int digit) {
        String roman = "";
        int thousandDigit = digit/1000;
        int hundredDigit = digit %1000;

        return convertNormalDigitalToRoman(thousandDigit, hundredDigit, 4);
    }
}
