package test1.redPackageTest;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RedPackageTest2 {
    public static List<BigDecimal> getRandomGifts(long money, int num) {
        if (Long.valueOf(money).intValue() == num) {
            List<BigDecimal> result = Lists.newArrayList();
            for (int i = 0; i < num; i++) {
                BigDecimal divisor = new BigDecimal(0.01);
                result.add(divisor);
            }
            return result;
        }

        Random random = new Random();
        List<Long> list = Lists.newArrayList();
        int count = num;
        for (int i = 0; i < count - 1; i++) {
            long sed = money / num * 2;
            long rd = random.nextInt(Math.abs((int) (sed - 2))) + 1;
            list.add(rd);
            money -= rd;
            num--;
        }
        list.add(money);

        BigDecimal divisor = new BigDecimal(100);
        return list.stream().map(gift -> new BigDecimal(String.valueOf(gift)).divide(divisor)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(getRandomGifts(300,100));//money单位为分
    }
}
