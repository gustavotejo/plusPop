package core;

import java.time.LocalDate;

import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;
import exceptions.NomeUsuarioException;
import exceptions.SenhaIncorretaException;
import exceptions.SenhaInvalidaException;
import exceptions.SenhaProtegidaException;
import util.ValidaDadosDoUsuario;

/**
 * Essa classe representa um Usuario do +Pop.
 * 
 * @author matteus
 *
 */
public class Usuario {
	private static final String IMAGEM_DEFAULT = "resources/default.jpg";
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNasc;
	private String imagem;

	/**
	 * Construtor de Usuario.
	 * 
	 * @param nome
	 *            Indica o nome de Usuario
	 * @param email
	 *            String com o e-mail do Usuario
	 * @param senha
	 *            String com a senha
	 * @param dataNasc
	 *            Um objeto LocalDate contendo a data de nascimento do Usuario
	 * @param imagem
	 *            String contendo o caminho para a imagem de perfil do Usuario
	 * @throws NomeUsuarioException
	 *             Excessão lançada quando o parametro nome é inválido.
	 * @throws EmailInvalidoException
	 *             Excessão lançada quando o parametro e-mail é inválido.
	 * @throws DataNaoExisteException
	 *             Excessão lançada quando a data passada no parametro dataNasc
	 *             não existe.
	 * @throws FormatoDeDataInvalidoException
	 *             Excessão lançada quando o formato do parametro dataNasc é
	 *             inválido.
	 */
	public Usuario(String nome, String email, String senha, String dataNasc, String imagem) throws NomeUsuarioException,
			EmailInvalidoException, FormatoDeDataInvalidoException, DataNaoExisteException {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setImagem(imagem);
		setDataNasc(dataNasc);
	}

	private void setDataNasc(String dataNasc) throws FormatoDeDataInvalidoException, DataNaoExisteException {
		this.dataNasc = ValidaDadosDoUsuario.validaData(dataNasc);
	}

	private void setImagem(String imagem) {
		if (isStringVazia(imagem)) {
			if (isStringVazia(this.imagem)) {
				this.imagem = IMAGEM_DEFAULT;
			}
		} else {
			this.imagem = imagem;
		}
	}

	/**
	 * Atribui uma nova senha para um usuário.
	 * 
	 * @param senha
	 *            Valor a ser atribuio a senha do usuário.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Retorna o valor de um atributo de Usuario.
	 * 
	 * @param atributo
	 *            String que indica qual o atributo a retornar.
	 * @return atributo indicado no parametro atributo.
	 * @throws SenhaProtegidaException
	 *             Excessão lançada se o atributo for senha, pois a senha é
	 *             protegida.
	 */
	public String getInfo(String atributo) throws SenhaProtegidaException {
		switch (atributo.toUpperCase()) {
		case "NOME":
			return this.nome;
		case "DATA DE NASCIMENTO":
			return this.dataNasc.toString();
		case "FOTO":
			return this.imagem;
		case "SENHA":
			throw new SenhaProtegidaException();
		default:
			return "Este atributo e invalido!";
		}
	}

	/**
	 * Retona o e-mail de Usuario.
	 * 
	 * @return Atributo e-mail de Usuario.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Retorna a senha de Usuario sem lançar excessão.
	 * 
	 * @return Retorna o parametro senha de Usuario.
	 */
	public String getSenha() {
		return this.senha;
	}

	/**
	 * Define um novo valor pra um atributo de Usuario.
	 * 
	 * @param atributo
	 *            String que define qual atributo vai ser mudado.
	 * @param valor
	 *            Novo valor do atributo selecionado.
	 * @throws NomeUsuarioException
	 *             Excessão lançada caso o método tente mudar o nome para um
	 *             valor inválido.
	 * @throws EmailInvalidoException
	 *             Excessão lançada caso o método tente mudar o e-mail para um
	 *             valor inválido.
	 * @throws FormatoDeDataInvalidoException
	 *             Excessão lançada caso o método tente mudar dataNasc para uma
	 *             data com formato inválido.
	 * @throws DataNaoExisteException
	 *             Excessão lançada caso o método tente mudar dataNasc para uma
	 *             data que não existe.
	 */
	public void setAtributo(String atributo, String valor) throws NomeUsuarioException, EmailInvalidoException,
			FormatoDeDataInvalidoException, DataNaoExisteException {
		switch (atributo.toUpperCase()) {
		case "NOME":
			setNome(valor);
			break;
		case "E-MAIL":
			setEmail(valor);
			break;
		case "FOTO":
			setImagem(valor);
			break;
		case "DATA DE NASCIMENTO":
			setDataNasc(valor);
			break;
		default:
			break;
		}
	}

	/**
	 * Verifica se a senha digitada é válida.
	 * 
	 * @param senhaDigitada
	 *            Senha a ser validada.
	 * @throws SenhaIncorretaException
	 *             Excessão lançada caso a senha esteja incorreta.
	 */
	public void isSenhaCorreta(String senhaDigitada) throws SenhaIncorretaException {
		if (!this.senha.equals(senhaDigitada)) {
			throw new SenhaIncorretaException();
		}
	}

	/**
	 * Verifica se a senha de login é válida.
	 * 
	 * @param senhaDigitada
	 *            Senha digitada pelo usuário.
	 * @throws SenhaInvalidaException
	 *             Excessão lançada caso a senha não confira com a senha
	 *             validada.
	 */
	public void validaSenhaLogin(String senhaDigitada) throws SenhaInvalidaException {
		try {
			isSenhaCorreta(senhaDigitada);
		} catch (SenhaIncorretaException e) {
			throw new SenhaInvalidaException();
		}
	}

	// Refatoramentos:

	private void setEmail(String email) throws EmailInvalidoException {
		ValidaDadosDoUsuario.validaEmail(email);
		this.email = email;
	}

	private void setNome(String nome) throws NomeUsuarioException {
		if (isStringVazia(nome))
			throw new NomeUsuarioException();
		this.nome = nome;
	}

	private boolean isStringVazia(String string) {
		return string == null || string.trim().equals("");
	}
}
