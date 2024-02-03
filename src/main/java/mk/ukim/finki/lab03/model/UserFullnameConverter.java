package mk.ukim.finki.lab03.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserFullnameConverter implements AttributeConverter<UserFullname, String> {
    private static final String SEPARATOR=", ";

    @Override
    public String convertToDatabaseColumn(UserFullname userFullname) {
        if (userFullname==null)
            return null;
        StringBuilder sb=new StringBuilder();
        if (userFullname.getName()!=null && !userFullname.getName().isEmpty()){
            sb.append(userFullname.getName());
            sb.append(SEPARATOR);
        }
        if (userFullname.getSurname()!=null && !userFullname.getSurname().isEmpty()){
            sb.append(userFullname.getSurname());
        }

        return sb.toString();
    }

    @Override
    public UserFullname convertToEntityAttribute(String fullName) {
        if (fullName == null || fullName.isEmpty())
            return null;

        String[] pieces = fullName.split(SEPARATOR);
        if (pieces == null || pieces.length == 0) {
            return null;
        }
        UserFullname userFullname = new UserFullname();
        String name = !pieces[0].isEmpty() ? pieces[0] : null;
        if (fullName.contains(SEPARATOR)) {
            userFullname.setName(name);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                userFullname.setSurname(pieces[1]);
            }
        } else {
            userFullname.setSurname(name);
        }
        return userFullname;
    }

}
