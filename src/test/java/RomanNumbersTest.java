import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * ��������ת��������
 * ��ᵽ���㣺
 * 1 ����������ô���֣����ಢ�������п����ԣ������ܱ���5000�Σ�д5000��equals�������ʱû���뷨��ֻ���Ȱݶ��굼����ġ���Ч����������
 * 2 �ع������ף�Ҫ����ϰ
 */
public class RomanNumbersTest {

    @Test
    /**
     * ������������5000���µ�����ת�����������������趨
     */
    public void convert_digitals_to_Roman(){
        RomanNumber romanNumber = new RomanNumber();
        //��λ��
        String roman = romanNumber.convertDigitalToRoman(1);
        assertEquals("I", roman);
        roman = romanNumber.convertDigitalToRoman(2);
        assertEquals("II", roman);
        roman = romanNumber.convertDigitalToRoman(3);
        assertEquals("III", roman);
        roman = romanNumber.convertDigitalToRoman(4);
        assertEquals("IV", roman);
        roman = romanNumber.convertDigitalToRoman(5);
        assertEquals("V", roman);
        roman = romanNumber.convertDigitalToRoman(6);
        assertEquals("VI", roman);
        roman = romanNumber.convertDigitalToRoman(7);
        assertEquals("VII", roman);
        roman = romanNumber.convertDigitalToRoman(8);
        assertEquals("VIII", roman);
        roman = romanNumber.convertDigitalToRoman(9);
        assertEquals("IX", roman);
        //ʮλ��
        roman = romanNumber.convertDigitalToRoman(10);
        assertEquals("X", roman);
        roman = romanNumber.convertDigitalToRoman(22);
        assertEquals("XXII", roman);
        roman = romanNumber.convertDigitalToRoman(33);
        assertEquals("XXXIII", roman);
        roman = romanNumber.convertDigitalToRoman(40);
        assertEquals("XL", roman);
        roman = romanNumber.convertDigitalToRoman(50);
        assertEquals("L", roman);
        roman = romanNumber.convertDigitalToRoman(66);
        assertEquals("LXVI", roman);
        roman = romanNumber.convertDigitalToRoman(77);
        assertEquals("LXXVII", roman);
        roman = romanNumber.convertDigitalToRoman(88);
        assertEquals("LXXXVIII", roman);
        roman = romanNumber.convertDigitalToRoman(90);
        assertEquals("XC", roman);
        roman = romanNumber.convertDigitalToRoman(99);
        assertEquals("XCIX", roman);
        //��λ��
        roman = romanNumber.convertDigitalToRoman(100);
        assertEquals("C", roman);
        roman = romanNumber.convertDigitalToRoman(222);
        assertEquals("CCXXII", roman);
        roman = romanNumber.convertDigitalToRoman(333);
        assertEquals("CCCXXXIII", roman);
        roman = romanNumber.convertDigitalToRoman(400);
        assertEquals("CD", roman);
        roman = romanNumber.convertDigitalToRoman(500);
        assertEquals("D", roman);
        roman = romanNumber.convertDigitalToRoman(666);
        assertEquals("DCLXVI", roman);
        roman = romanNumber.convertDigitalToRoman(777);
        assertEquals("DCCLXXVII", roman);
        roman = romanNumber.convertDigitalToRoman(888);
        assertEquals("DCCCLXXXVIII", roman);
        roman = romanNumber.convertDigitalToRoman(900);
        assertEquals("CM", roman);
        roman = romanNumber.convertDigitalToRoman(999);
        assertEquals("CMXCIX", roman);

        roman = romanNumber.convertDigitalToRoman(901);
        assertEquals("CMI", roman);
        //ǧλ��
        roman = romanNumber.convertDigitalToRoman(1000);
        assertEquals("M", roman);
        roman = romanNumber.convertDigitalToRoman(2000);
        assertEquals("MM", roman);
        roman = romanNumber.convertDigitalToRoman(3000);
        assertEquals("MMM", roman);
        roman = romanNumber.convertDigitalToRoman(4000);
        assertEquals("MMMM", roman);

        roman = romanNumber.convertDigitalToRoman(1990);
        assertEquals("MCMXC", roman);
        roman = romanNumber.convertDigitalToRoman(2008);
        assertEquals("MMVIII", roman);
        roman = romanNumber.convertDigitalToRoman(2018);
        assertEquals("MMXVIII", roman);
        roman = romanNumber.convertDigitalToRoman(2108);
        assertEquals("MMCVIII", roman);
    }

    @Test
    /**
     * ���Ը�λ��
     */
    public void convert_one_digital_to_Roman(){
        RomanNumber romanNumber = new RomanNumber();
        String roman = romanNumber.convertOneDigitalToRoman(1);
        assertEquals("I", roman);
        roman = romanNumber.convertOneDigitalToRoman(2);
        assertEquals("II", roman);
        roman = romanNumber.convertOneDigitalToRoman(3);
        assertEquals("III", roman);
        roman = romanNumber.convertOneDigitalToRoman(4);
        assertEquals("IV", roman);
        roman = romanNumber.convertOneDigitalToRoman(5);
        assertEquals("V", roman);
        roman = romanNumber.convertOneDigitalToRoman(6);
        assertEquals("VI", roman);
        roman = romanNumber.convertOneDigitalToRoman(7);
        assertEquals("VII", roman);
        roman = romanNumber.convertOneDigitalToRoman(8);
        assertEquals("VIII", roman);
        roman = romanNumber.convertOneDigitalToRoman(9);
        assertEquals("IX", roman);
    }

    @Test
    /**
     * ����ʮλ��
     */
    public void convert_two_digitals_to_Roman(){
        RomanNumber romanNumber = new RomanNumber();
        String roman = romanNumber.convertTwoDigitalToRoman(10);
        assertEquals("X", roman);
        roman = romanNumber.convertTwoDigitalToRoman(22);
        assertEquals("XXII", roman);
        roman = romanNumber.convertTwoDigitalToRoman(33);
        assertEquals("XXXIII", roman);
        roman = romanNumber.convertTwoDigitalToRoman(40);
        assertEquals("XL", roman);
        roman = romanNumber.convertTwoDigitalToRoman(50);
        assertEquals("L", roman);
        roman = romanNumber.convertTwoDigitalToRoman(66);
        assertEquals("LXVI", roman);
        roman = romanNumber.convertTwoDigitalToRoman(77);
        assertEquals("LXXVII", roman);
        roman = romanNumber.convertTwoDigitalToRoman(88);
        assertEquals("LXXXVIII", roman);
        roman = romanNumber.convertTwoDigitalToRoman(90);
        assertEquals("XC", roman);
        roman = romanNumber.convertTwoDigitalToRoman(99);
        assertEquals("XCIX", roman);
    }

    @Test
    /**
     * ���԰�λ��
     */
    public void convert_three_digitals_to_Roman(){
        RomanNumber romanNumber = new RomanNumber();
        String roman = romanNumber.convertThreeDigitalToRoman(100);
        assertEquals("C", roman);
        roman = romanNumber.convertThreeDigitalToRoman(222);
        assertEquals("CCXXII", roman);
        roman = romanNumber.convertThreeDigitalToRoman(333);
        assertEquals("CCCXXXIII", roman);
        roman = romanNumber.convertThreeDigitalToRoman(400);
        assertEquals("CD", roman);
        roman = romanNumber.convertThreeDigitalToRoman(500);
        assertEquals("D", roman);
        roman = romanNumber.convertThreeDigitalToRoman(666);
        assertEquals("DCLXVI", roman);
        roman = romanNumber.convertThreeDigitalToRoman(777);
        assertEquals("DCCLXXVII", roman);
        roman = romanNumber.convertThreeDigitalToRoman(888);
        assertEquals("DCCCLXXXVIII", roman);
        roman = romanNumber.convertThreeDigitalToRoman(900);
        assertEquals("CM", roman);
        roman = romanNumber.convertThreeDigitalToRoman(999);
        assertEquals("CMXCIX", roman);
        //��������
        roman = romanNumber.convertThreeDigitalToRoman(901);
        assertEquals("CMI", roman);
    }

    @Test
    /**
     * ����ǧλ��
     */
    public void convert_four_digitals_to_Roman(){
        RomanNumber romanNumber = new RomanNumber();
        String roman = romanNumber.convertFourDigitalToRoman(1000);
        assertEquals("M", roman);
        roman = romanNumber.convertFourDigitalToRoman(2000);
        assertEquals("MM", roman);
        roman = romanNumber.convertFourDigitalToRoman(3000);
        assertEquals("MMM", roman);
        roman = romanNumber.convertFourDigitalToRoman(4000);
        assertEquals("MMMM", roman);
        //��������
        roman = romanNumber.convertFourDigitalToRoman(1990);
        assertEquals("MCMXC", roman);
        roman = romanNumber.convertFourDigitalToRoman(2008);
        assertEquals("MMVIII", roman);
        roman = romanNumber.convertFourDigitalToRoman(2018);
        assertEquals("MMXVIII", roman);
    }

}
