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
        try {
            System.out.println("1025->"+getFormatSize(1025D,SizeTypeEnum.B));
            System.out.println("3025->"+getFormatSize(3025D,SizeTypeEnum.KB));
            System.out.println("10251->"+getFormatSize(10251D,SizeTypeEnum.B));
            System.out.println("1025111->"+getFormatSize(1025111D,SizeTypeEnum.B));
            System.out.println("1025111111->"+getFormatSize(1025111111D,SizeTypeEnum.B));
            System.out.println("102511111111111->"+getFormatSize(102511111111111D,SizeTypeEnum.KB));
            System.out.println("102511111111111111111111->"+getFormatSize(102511111111111111111111D,SizeTypeEnum.B));
            System.out.println("1025111111111111111111111111111->"+getFormatSize(1025111111111111111111111111111D,SizeTypeEnum.B));
            System.out.println("1025111111111111111111111111111111111->"+getFormatSize(1025111111111111111111111111111111111D,SizeTypeEnum.B));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getFormatSize(Double size,SizeTypeEnum startType) throws Exception {
        if(startType.getType()<SizeTypeEnum.B.getType()||startType.getType()>SizeTypeEnum.PB.getType()){
            throw new Exception("");
        }
        if(size<1024||startType.getType()==SizeTypeEnum.PB.getType()){
            return new BigDecimal(Double.toString(size)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + SizeTypeEnum.getUnit(startType.getType());
        }else{
            size/=1024;
            return getFormatSize(size,SizeTypeEnum.of(startType.getType()+1));
        }
    }

}
