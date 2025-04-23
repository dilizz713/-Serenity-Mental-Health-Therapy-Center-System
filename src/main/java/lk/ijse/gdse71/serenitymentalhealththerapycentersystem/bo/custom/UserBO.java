package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.UsersDTO;

public interface UserBO extends SuperBO{
    String getNextUserId();

    boolean saveUser(UsersDTO usersDTO);
}
