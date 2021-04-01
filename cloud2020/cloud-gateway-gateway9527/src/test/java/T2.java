import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

/**
 * @author lx
 * @date 2021/3/19 21:51
 */
@SpringBootTest
public class T2 {

    @Test
    public void t1(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        //2021-03-19T21:53:10.858+08:00[Asia/Shanghai]
    }

    @Test
    public void t2(){
        int[] arr = {1,2,3,4,3,2,1};

        int result=0;
        for(int i=0;i<arr.length;i++){
            result^=arr[i];
        }

        System.out.println(result);
        System.out.println(0^1);
        System.out.println(1^2);
    }
}
