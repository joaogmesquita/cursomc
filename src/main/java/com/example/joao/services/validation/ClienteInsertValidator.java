package com.example.joao.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.joao.domain.enums.TipoCliente;
import com.example.joao.dto.ClienteNewDTO;
import com.example.joao.resources.exception.FieldMessage;
import com.example.joao.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCnpfj())) {
			list.add(new FieldMessage("cpfoucnpj","CPF inválido"));

		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && BR.isValidCPF(objDto.getCpfCnpfj())) {
			
			list.add(new FieldMessage("cpfoucnpj","CNPJ inválido"));

		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}