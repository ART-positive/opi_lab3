package classes.web_3_new;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 * Валидатор для проверки числового ввода.
 *
 * @author Березовский Артемий
 * @version 1.0
 * @since 04.05.2025
 */
@FacesValidator("isNumberValidator")
public class IsNumberValidator implements Validator {

    /**
     * Проверяет, является ли значение числом.
     * @throws ValidatorException если значение не может быть преобразовано в Double.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            Double.parseDouble(value.toString());
        }catch (NumberFormatException e){
            FacesMessage fm = new FacesMessage("Input must only include numbers");
            throw new ValidatorException(fm);
        }
    }
}
