package com.financaspessoais.util;

public class Constantes {
	
	public static final Short ID_PERFIL_PADRAO = new Short("2");
	public static final String NOME_TIPO_DESPESA_RECEITA_TRANSFERENCIA = "Transferência";
	public static final String USUARIO_LOGADO = "usuarioLogado";
	public static final String MSG_USUARIO_INVALIDO = "Verifique seu login e senha.";
	public static final String MSG_ERRO_GENERICA = "Erro, contate o Administrador.";
	public static final String MSG_ERRO_TIPO_RECEITA_TIPO_DESPESA_MESMO_TEMPO = "Um lançamento só pode possuir um Tipo de Receita ou um Tipo despesa, não pode possuir ambos.";
	public static final String MSG_DUPLICIDADE_USUARIO_1 = "Já existe um usuário com este login.";
	public static final String MSG_DUPLICIDADE_USUARIO_2 = "Escolha um outro login.";
	public static final String MSG_DUPLICIDADE_TIPO_RECEITA = "Já existe um Tipo de Receita com este nome.";
	public static final String MSG_DUPLICIDADE_ESTABELECIMENTO = "Já existe um Estabelecimento com este nome.";
	public static final String MSG_DUPLICIDADE_CARTAO_DE_CREDITO = "Já existe um Cartão de Crédito com este nome.";
	public static final String MSG_DUPLICIDADE_TIPO_DESPESA = "Já existe um Tipo de Despesa com este nome.";
	public static final String MSG_DUPLICIDADE_BANCO = "Já existe um Banco com este nome.";
	public static final String MSG_DUPLICIDADE_FATURA_CARTAO = "Já existe uma Fatura de Cartão de Crédito com estes mesmos cartão, ano e mês!";
	public static final String MSG_EXCLUSAO_BEM_SUCEDIDA = "Exclusão realizada com sucesso.";
	public static final String MSG_CAMPO_OBRIGATORIO = "Campo obrigatório não preenchido.";
	public static final String MSG_VALORES_INCONSISTENTES = "Alguns valores estão inconsistentes";
	public static final String MSG_VALOR_INVALIDO = "Campo preenchido com valor inválido.";
	
	public static final String MSG_VALIDACAO_DATA_PGTO_NULA_VALOR_PAGO_PREENCHIDO = "Para preencher o valor pago, é necessário preencher também a Data de Pagamento.";
	public static final String MSG_VALIDACAO_VALOR_PAGO_NULO_DATA_PGTO_PREENCHIDA = "Para preencher a data de pagamento, é necessário preencher também o valor pago.";
	
	public static final String MSG_PREENCHER_NOME = "Favor preencher o Nome.";
	public static final String MSG_PREENCHER_BANDEIRA = "Favor preencher o campo \"Bandeira\" do Cartão.";
	public static final String MSG_PREENCHER_SOBRENOME = "Favor preencher o Sobrenome.";
	public static final String MSG_PREENCHER_LOGIN = "Favor preencher o Login.";
	public static final String MSG_PREENCHER_SENHA = "Favor preencher a Senha.";
	public static final String MSG_PREENCHER_CPF = "Favor preencher o CPF.";
	public static final String MSG_PREENCHER_AVULSO = "Favor preencher \"É Avulso?\".";
	public static final String MSG_PREENCHER_NUMERO_PARCELA = "Favor preencher o campo \"Número da Parcela\".";
	public static final String MSG_PREENCHER_VALOR = "Favor preencher o campo \"Valor\".";
	public static final String MSG_PREENCHER_CONTAS = "Favor preencher ambos os campos \"Conta Origem\" e \"Conta Destino\".";
	public static final String MSG_PREENCHER_CONTA = "Favor preencher o campo \"Conta\".";
	public static final String MSG_PREENCHER_CARTAO = "Favor preencher o campo \"Cartão de Crédito\".";
	public static final String MSG_PREENCHER_FATURA_CARTAO = "Favor preencher o campo \"Fatura de Cartão de Crédito\".";
	public static final String MSG_PREENCHER_LANCAMENTO_CARTAO = "Favor preencher o Lançamento de Cartão de Crédito.";
	public static final String MSG_PREENCHER_TIPO_LANCAMENTO = "Favor preencher o campo \"Tipo de Lançamento\".";
	public static final String MSG_PREENCHER_ANO_DA_FATURA = "Favor preencher o campo \"Ano da Fatura\".";
	public static final String MSG_PREENCHER_MES_DA_FATURA = "Favor preencher o campo \"Mês da Fatura\".";
	public static final String MSG_PREENCHER_DATA_VENCIMENTO = "Favor preencher o campo \"Data de Vencimento\".";
	public static final String MSG_PREENCHER_DATA_TRANSFERENCIA = "Favor preencher o campo \"Data de Transferência\".";
	
	public static final String MSG_CAMPO_INVALIDO_CONTAS_IGUAIS = "Conta de Origem tem que ser diferente de conta de destino";
	public static final String MSG_CAMPO_INVALIDO_ANO = "Preencha o campo \"Ano\" com um valor maior ou igual a 2000.";
	public static final String MSG_CAMPO_INVALIDO_DATA_REALIZACAO = "Data de Realização tem que ser menor ou igual a data atual.";
	public static final String MSG_CAMPO_INVALIDO_VALOR_LANCAMENTO = "O valor do lançamento tem que ser maior que zero.";
}
