package id.co.alamisharia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Integer id;
    private String name;
    private String age;
    private Integer balanced;
    private Integer previousBalanced;
    private Integer averageBalanced;
    private Integer freeTransfer;
    private String threadNo1;
    private String threadNo2a;
    private String threadNo2b;
    private String threadNo3;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(",");
        sb.append(name);
        sb.append(",");
        sb.append(age);
        sb.append(",");
        sb.append(balanced);
        sb.append(",");
        sb.append(threadNo2b);
        sb.append(",");
        sb.append(threadNo3);
        sb.append(",");
        sb.append(previousBalanced);
        sb.append(",");
        sb.append(averageBalanced);
        sb.append(",");
        sb.append(threadNo1);
        sb.append(",");
        sb.append(freeTransfer);
        sb.append(",");
        sb.append(threadNo2a);
        return sb.toString();
    }

}
