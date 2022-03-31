package com.example.register.model.validator;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		Properties props = new Properties();
		try (var propertiesStream = new FileInputStream("src/main/resources/messages.properties")) {
		props.load(propertiesStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		MessageResolver resolver = new PropertiesMessageResolver(props);
		
		PasswordValidator validator = new PasswordValidator(resolver, List.of(
				new LengthRule(5, 255),
				new CharacterRule(EnglishCharacterData.Digit, 1),
				new RepeatCharacterRegexRule(3)
				));
		
		RuleResult result = validator.validate(new PasswordData(password));
        
		if (result.isValid())
            return true;

        List<String> messages = validator.getMessages(result);
        
        String messageTemplate = String.join(" ", messages);
        getConstraintViolationBuilder(context, messageTemplate)
        	.addConstraintViolation()
        	.disableDefaultConstraintViolation();
		return false;
	}

	private ConstraintValidatorContext.ConstraintViolationBuilder getConstraintViolationBuilder(ConstraintValidatorContext context, String messageTemplate) {
		return context.buildConstraintViolationWithTemplate(messageTemplate);
	}

}
