package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private  int id;
    private String fullName;
    private  String phoneNumber;
    private String password;
    private List<Role> role;






}
