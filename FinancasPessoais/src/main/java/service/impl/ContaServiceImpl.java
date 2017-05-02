package service.impl;

import javax.faces.application.FacesMessage;

import dao.ContaDAO;
import dominio.Conta;
import dominio.Usuario;
import service.ContaService;

public class ContaServiceImpl implements ContaService {

	private ContaDAO contaDAO = new ContaDAO();
	
	@Override
	public Conta getContaPeloId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inserirConta(Conta conta) {
		boolean retorno;
		FacesMessage mensagem = null;
		Conta novaConta = null;
		
		novaConta = contaDAO.inserirConta(conta);
		
		return true;
	}

	@Override
	public boolean deletarConta(Conta conta) {
		// TODO Auto-generated method stub
		return false;
	}

}
