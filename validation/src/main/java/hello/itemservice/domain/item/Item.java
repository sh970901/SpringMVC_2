package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang="javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원 넘게 입력부탁드립니다.")
//컨트롤러에서 자바코드로 처리하는 것도 괜찮은 방법 (Object Error의 경우)
public class Item {

    private Long id;
    @NotBlank
    private String itemName;
    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;
    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
