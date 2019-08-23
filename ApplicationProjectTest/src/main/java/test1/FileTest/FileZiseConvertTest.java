package test1.FileTest;

import java.math.BigDecimal;

/**
 * Created by liaura_ljl on 2019/8/23.
 */
public class FileZiseConvertTest {
    public static void main(String[] args){
        new FileZiseConvertTest().start();
    }

    /**
     * 0:B 1:KB 2:MB 3:GB 4:TB 5:PB
     */
    private void start(){
        System.out.println("1025->"+getFormatSize(1025D,SizeTypeEnum.B));
        System.out.println("3025->"+getFormatSize(3025D,SizeTypeEnum.KB));
        System.out.println("10251->"+getFormatSize(10251D,SizeTypeEnum.B));
        System.out.println("1025111->"+getFormatSize(1025111D,SizeTypeEnum.B));
        System.out.println("1025111111->"+getFormatSize(1025111111D,SizeTypeEnum.B));
        System.out.println("102511111111111->"+getFormatSize(102511111111111D,SizeTypeEnum.KB));
        System.out.println("102511111111111111111111->"+getFormatSize(102511111111111111111111D,SizeTypeEnum.B));
        System.out.println("1025111111111111111111111111111->"+getFormatSize(1025111111111111111111111111111D,SizeTypeEnum.B));
        System.out.println("1025111111111111111111111111111111111->"+getFormatSize(1025111111111111111111111111111111111D,SizeTypeEnum.B));

    }

    private String getFormatSize(Double size,SizeTypeEnum startType) {
        Double kiloByte = size/1024;
        if(kiloByte < 1) {
            return size + SizeTypeEnum.getUnit(startType.getType());
        }

        Double megaByte = kiloByte/1024;
        if(megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + SizeTypeEnum.getUnit(startType.getType()+1);
        }

        Double gigaByte = megaByte/1024;
        if(gigaByte < 1) {
            BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        Double teraBytes = gigaByte/1024;
        if(teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
