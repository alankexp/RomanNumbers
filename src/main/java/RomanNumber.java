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

 * 完成的代码是随时重构后的结果，不一定是最完善的，但是已基本达到嗅不到坏味道
 * 重新复习了TDD，总结一下：
 * a 首先划分归类测试用例，个位数用例|十位数用例|百位数用例|千位数用例。
 * b 从最简单的个位数测试用例出发，keep simple, stupid!找出部分规律，分别是特殊数字4,5,9和其他数字
 * c 接着实现十位数，发现十位数需要嵌套个位数的函数一起实现转换功能
 * d 接着实现百位数，发现百位数需要嵌套十位数的函数一起实现转换功能
 * e 接着实现千位数，发现千位数需要嵌套百位数的函数一起实现转换功能，但又有存在不同，不需要对4,5,9特殊数字做处理
 * f 测试用例发现，当使用高位都不为0的数字，测试都能通过，例如4321；但是使用4021或者4001测试，失败了
 * g 体会到有效测试用例的重要性，怎么样才能有效测试，而又不可能遍历5000次，写5000个equals，这个暂时没有想法
 * h 当解决了步骤f发现的问题后，已经基本可以实现需求目标了，过程使用了隐含递归以及各种重构动作
 * i 当所有测试用例通过之后（我自认为所有，可能不完整），又发现相同的代码块，OK，DRP！继续重构
 * j 本来是需要4个函数来分别实现个位，十位，百位，千位，分别是：
 * convertOneDigitalToRoman|convertTwoDigitalToRoman|convertThreeDigitalToRoman|convertFourDigitalToRoman
 * 重构之后改成一个函数：
 * convertDigitalToRomanByDigitalLength
 * 重构的过程不是很有信心，但是有TDD保证，还是一步步很清晰的实现了目标。最终就是4个方法解决了
 * convertDigitalToRoman
 * convertDigitalToRomanByDigitalLength
 * convertSpecialDigit459
 * convertNormalDigitalToRoman
 */
public class RomanNumber {
    String[] smallerThanFive = {"I","X","C","M"};
    String[] largerThanFive =  {"V","L","D"};

    /**
     * 转换入口函数（隐含递归逻辑）
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
     * 根据数字长度转换罗马数字（整合个位到千位数）
     * @param digit
     * @param len 位数，例如个位数1，十位数2，百位数3，千位数4
     * @return
     */
    public String convertDigitalToRomanByDigitalLength(int digit, int len){
        int tenspow = (int)Math.pow(10,len-1);
        int preDigit = digit/tenspow;//待转换数字的最高位
        int nextDigit = digit%tenspow;//待转换数字的次高位
        if (len != 4){//千位数不需要对特殊数字做处理
            String x = convertSpecialDigit459(preDigit, nextDigit, len);
            if (x != null)
                return x;
        }
        return convertNormalDigitalToRoman(preDigit, nextDigit, len);
    }

    /**
     * 转换特殊的4,5,9开头的数字
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
     * 转换除了4,5,9以外的数字
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
     * 转换个位数
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
     * 转换十位数
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
     * 转换百位数
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
     * 转换千位数
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
