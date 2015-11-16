package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.tiposDeUsuario.CelebridadePop;
import core.tiposDeUsuario.IconePop;
import core.tiposDeUsuario.Normal;
import core.tiposDeUsuario.TipoDeUsuario;
import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;
import exceptions.IndiceConteudoPostInvalido;
import exceptions.IndiceDePostNaoExisteException;
import exceptions.IndiceMenorQueZeroException;
import exceptions.NaoHaNotificacoesException;
import exceptions.NaoTemAmizadeException;
import exceptions.NomeUsuarioException;
import exceptions.PostTalNaoExisteException;
import exceptions.RequisicaoInvalidaException;
import exceptions.SenhaIncorretaException;
import exceptions.SenhaInvalidaException;
import exceptions.SenhaProtegidaException;
import exceptions.SolicitacaoInexistenteException;
import util.ValidaDadosDoUsuario;

/**
 * Essa classe representa um Usuario do +Pop.
 * 
 * @author matteus
 *
 */
public class Usuario implements Comparable<Usuario> {
	private static final String IMAGEM_DEFAULT = "resources/default.jpg";
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNasc;
	private String imagem;
	private List<Post> mural;
	private List<String> notificacoes;
	private Set<Usuario> solicitacoesDeAmizade;
	private Set<Usuario> amigos;
	private int popularidade;
	private TipoDeUsuario tipoDeUsuario;

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
	 *             Excessao lancada quando o parametro nome e invalido.
	 * @throws EmailInvalidoException
	 *             Excessao lancada quando o parametro e-mail e invalido.
	 * @throws DataNaoExisteException
	 *             Excessao lancada quando a data passada no parametro dataNasc
	 *             nao existe.
	 * @throws FormatoDeDataInvalidoException
	 *             Excessao lancada quando o formato do parametro dataNasc e
	 *             invalido.
	 */
	public Usuario(String nome, String email, String senha, String dataNasc, String imagem) throws NomeUsuarioException,
			EmailInvalidoException, FormatoDeDataInvalidoException, DataNaoExisteException {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setImagem(imagem);
		setDataNasc(dataNasc);
		this.mural = new ArrayList<>();
		this.notificacoes = new ArrayList<>();
		this.solicitacoesDeAmizade = new HashSet<>();
		this.amigos = new HashSet<>();
		this.popularidade = 0;
		this.tipoDeUsuario = new Normal();
	}

	public void adicionaPost(Post post) {
		mural.add(post);
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
	 * Atribui uma nova senha para um usuario.
	 * 
	 * @param senha
	 *            Valor a ser atribuio a senha do usuario.
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
	 *             Excessao lancada se o atributo for senha, pois a senha e
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
	 * Retorna a senha de Usuario sem lancar excessao.
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
	 *             Excessao lancada caso o metodo tente mudar o nome para um
	 *             valor invalido.
	 * @throws EmailInvalidoException
	 *             Excessao lancada caso o metodo tente mudar o e-mail para um
	 *             valor invalido.
	 * @throws FormatoDeDataInvalidoException
	 *             Excessao lancada caso o metodo tente mudar dataNasc para uma
	 *             data com formato invalido.
	 * @throws DataNaoExisteException
	 *             Excessao lancada caso o metodo tente mudar dataNasc para uma
	 *             data que nao existe.
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
	 * Verifica se a senha digitada e valida.
	 * 
	 * @param senhaDigitada
	 *            Senha a ser validada.
	 * @throws SenhaIncorretaException
	 *             Excessao lancada caso a senha esteja incorreta.
	 */
	public void isSenhaCorreta(String senhaDigitada) throws SenhaIncorretaException {
		if (!this.senha.equals(senhaDigitada)) {
			throw new SenhaIncorretaException();
		}
	}

	/**
	 * Verifica se a senha de login e valida.
	 * 
	 * @param senhaDigitada
	 *            Senha digitada pelo usuario.
	 * @throws SenhaInvalidaException
	 *             Excessao lancada caso a senha nao confira com a senha
	 *             validada.
	 */
	public void validaSenhaLogin(String senhaDigitada) throws SenhaInvalidaException {
		try {
			isSenhaCorreta(senhaDigitada);
		} catch (SenhaIncorretaException e) {
			throw new SenhaInvalidaException();
		}
	}

	/**
	 * Metodo que retorna o atributo nome de um Usuario.
	 * 
	 * @return Retorna atributo nome de Usuario
	 */
	public String getNome() {
		return this.nome;
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

	/**
	 * Metodo que retorna o conteudo de um Post do mural de um Usuario.
	 * 
	 * @param post
	 *            Inteiro que indica o indice do Post.
	 * @return Retorna o toString de um Post.
	 * @throws RequisicaoInvalidaException
	 *             Excessao lancada caso nao exista post com o indice passado
	 *             como parametro ou quando o indice e menor que zero.
	 */
	public String getPost(int post) throws RequisicaoInvalidaException {
		if (post < 0)
			throw new RequisicaoInvalidaException(new IndiceMenorQueZeroException());
		if (post >= mural.size())
			throw new RequisicaoInvalidaException(new IndiceDePostNaoExisteException(post));
		return mural.get(post).toString();
	}

	/**
	 * Metodo que retorna mensagem, data ou hashtags de um Post de acordo com o
	 * atributo selecionado.
	 * 
	 * @param atributo
	 *            String que indica qual atributo do Post deve ser retornado
	 * @param post
	 *            Inteiro que indica o indice do Post.
	 * @return Retorna atributo do Post selecionado.
	 * @throws RequisicaoInvalidaException
	 *             Excessao lancada caso nao exista post com o indice passado
	 *             como parametro ou quando o indice e menor que zero.
	 */
	public String getPost(String atributo, int post) throws RequisicaoInvalidaException {
		if (post < 0)
			throw new RequisicaoInvalidaException(new IndiceMenorQueZeroException());
		if (post >= mural.size())
			throw new RequisicaoInvalidaException(new IndiceDePostNaoExisteException(post));
		switch (atributo.toUpperCase()) {
		case "MENSAGEM":
			return mural.get(post).passaConteudoPraString();
		case "DATA":
			return mural.get(post).getData();
		case "HASHTAGS":
			return mural.get(post).getHashtags();
		default:
			throw new RequisicaoInvalidaException();
		}
	}
	
	/**
	 * Metodo que retorna o conteudo de um determinado Post.
	 * 
	 * @param indice
	 * 			Inteiro que indica qual o conteudo do Post.
	 * @param post
	 * 			Inteiro que indica o indice do Post.
	 * @return	Retorna a string do conteudo pelo metodo.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada caso nao exista post com o indice passado
	 *          como parametro ou quando o indice e menor que zero.
	 * @throws IndiceConteudoPostInvalido
	 * 			Excessao lancada quando o indice nao existe.
	 */
	public String getConteudoPost(int indice, int post) throws RequisicaoInvalidaException, IndiceConteudoPostInvalido {
		if (post < 0 || indice < 0)
			throw new RequisicaoInvalidaException(new IndiceMenorQueZeroException());
		return mural.get(post).getConteudo(indice);
	}
	
	/**
	 * Metodo que adiciona o Usuario na lista de solicitacao de amizades.
	 * 
	 * @param usuarioLogado
	 * 			Usuario que sera adicionado na lista de solicitacao de amizade.
	 * 			
	 */
	public void adicionaSolicitacaoDeAmizade(Usuario usuarioLogado) {
		solicitacoesDeAmizade.add(usuarioLogado);
	}
	
	/**
	 * Metodo que adiciona novas notificacoes.
	 * 
	 * @param novaNotificacao
	 * 			String que sera adicionada na lista de novas notificacoes.
	 */
	public void adicionaNotificacao(String novaNotificacao) {
		notificacoes.add(novaNotificacao);
	}

	/**
	 * Metodo que retorna a quantidade de notificacoes.
	 * 
	 * @return Retorna um inteiro do tamanho da lista pelo metodo.
	 */
	public int getNotificacoes() {
		return notificacoes.size();
	}
	
	/**
	 * Metodo que remove a notificacao para capturar a seguinte e retorna-la.
	 * 
	 * @return Retorna o atributo com a string da notificacao.
	 * 
	 * @throws NaoHaNotificacoesException
	 * 			Excessao lancada quando a lista de notificacoes esta vazia.
	 */
	public String getNextNotificacao() throws NaoHaNotificacoesException {
		if (notificacoes.isEmpty())
			throw new NaoHaNotificacoesException();
		String notificacao = notificacoes.get(0);
		notificacoes.remove(0);
		return notificacao;
	}
	
	/**
	 * Metodo que remove o usuario selecionado da lista de soliciatacoes de amizades.
	 * 
	 * @param usuario
	 * 			Usuario que sera dado como parametro para ser removido.
	 * @throws SolicitacaoInexistenteException
	 * 			Excessao lancada quando o usuario nao solicitou amizade.
	 */
	public void rejeitaAmizade(Usuario usuario) throws SolicitacaoInexistenteException {
		if (naoTemNotificacaoDe(usuario))
			throw new SolicitacaoInexistenteException(usuario.getNome());
		solicitacoesDeAmizade.remove(usuario);
	}

	private boolean naoTemNotificacaoDe(Usuario usuario) {
		if (solicitacoesDeAmizade.contains(usuario))
			return false;
		return true;
	}
	
	/**
	 * Metodo que retorna a quantidade de amigos.
	 * 
	 * @return Retorna o tamanho da lista de amigos pelo metodo.
	 */
	public int getQtdAmigos() {
		return amigos.size();
	}

	/**
	 * Metodo que adiciona amigo e o remove das solicitacoes de amizade.
	 * 
	 * @param usuario
	 * 			Usuario dado como parametro para ser adicionado como amigo.
	 * @throws SolicitacaoInexistenteException
	 * 			Excessao lancada quando o usuario nao solicitou amizade.
	 */
	public void aceitaAmizade(Usuario usuario) throws SolicitacaoInexistenteException {
		if (naoTemNotificacaoDe(usuario))
			throw new SolicitacaoInexistenteException(usuario.getNome());
		adicionaAmigo(usuario);
		solicitacoesDeAmizade.remove(usuario);
	}

	private void adicionaAmigo(Usuario usuario) {
		amigos.add(usuario);
	}
	
	/**
	 * Metodo que verifica se a amizade nao existe.
	 * 
	 * @param usuario
	 * 			Usuario dado como parametro para verificar se ele nao está na lista de amigos.
	 * @throws NaoTemAmizadeException
	 * 			Excessao lancada quando o usuario nao esta na lista de amigos.
	 */
	public void verificaAmizade(Usuario usuario) throws NaoTemAmizadeException {
		if (!amigos.contains(usuario))
			throw new NaoTemAmizadeException(usuario);
	}
	
	/**
	 * Metodo que retorna o Post do mural de um Usuario.
	 * 
	 * @param post
	 * 			Inteiro que indica o indice do Post.
	 * @return Retorna o post atraves do metodo.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando o indice eh menor que zero.
	 * @throws PostTalNaoExisteException
	 * 			Excessao lancada quando o inteiro dado como parametro eh maior que a quantidade de posts
	 */
	public Post buscaPost(int post) throws RequisicaoInvalidaException, PostTalNaoExisteException {
		if (post < 0)
			throw new RequisicaoInvalidaException(new IndiceMenorQueZeroException());
		if (post >= getQuantidadeDePosts())
			throw new PostTalNaoExisteException(post, this.getQuantidadeDePosts());
		return mural.get(post);
	}

	private int getQuantidadeDePosts() {
		return mural.size();
	}
	
	/**
	 * Metodo que adiciona o usuario na lista de amigos.
	 * 
	 * @param usuario
	 * 			Usuario dado como parametro que sera adicionado na lista de amigos.
	 */
	public void adionaAmigo(Usuario usuario) {
		amigos.add(usuario);
	}
	
	/**
	 * Metodo que remove usuario da lista de amigos.
	 * 
	 * @param usuarioParaRemover
	 * 			Usuario dado como parametro que sera removido da lista de amigos.
	 */
	public void removeAmigo(Usuario usuarioParaRemover) {
		amigos.remove(usuarioParaRemover);
	}
	
	/**
	 * Metodo para curtir o post com variacao do tipo de usuario.
	 * 
	 * @param post
	 * 			Post dado como parametro que sera curtido.
	 * @param usuarioAmigo
	 * 			Usuario dado como parametro que tera o post curtido.
	 */
	public void curtirPost(Post post, Usuario usuarioAmigo) {
		tipoDeUsuario.curtirPost(post, usuarioAmigo);
	}
	
	/**
	 * Metodo para rejeitar o post com variacao do tipo de usuario.
	 * 
	 * @param post
	 * 			Post dado como parametro que sera rejeitado
	 * @param usuarioAmigo
	 * 			Usuario dado como parametro que tera o post rejeitado.
	 */
	public void rejeitarPost(Post post, Usuario usuarioAmigo) {
		tipoDeUsuario.rejeitarPost(post, usuarioAmigo);
	}
	
	/**
	 * Metodo que adiciona a popularidade a partir da quantidade de popularidades criara um novo tipo de Usuario.
	 * 
	 * @param pops
	 * 			Inteiro dado como parametro que sera somado no atributo para saber qual objeto criar.
	 */
	public void adicionaPops(int pops) { // Usar um singleton para evitar varios
											// objetos criados.
		popularidade += pops;
		if (popularidade < 500)
			tipoDeUsuario = new Normal();
		else if (popularidade >= 500 && popularidade <= 1000)
			tipoDeUsuario = new CelebridadePop();
		else
			tipoDeUsuario = new IconePop();
	}
	
	/**
	 * Metodo que retorna o nome do tipo de Usuario
	 * 
	 * @return Retorna o toString do tipo de Usuario.
	 */
	public String getPopularidade() {
		return tipoDeUsuario.toString();
	}
	
	/**
	 * Metodo que retorna a quantidade de pops.
	 * 
	 * @return Retorna o atributo de popularidade.
	 */
	public int getPops() {
		return this.popularidade;
	}
	
	/**
	 * Metodo que diminue em inteiro uma determinada quantidade de popularidade.
	 * 
	 * @param valor
	 * 			Inteiro dado como parametro para este valor ser diminuido do atributo de popularidade.
	 */
	public void removePops(int valor) {
		popularidade -= valor;
	}
	
	@Override
	public int compareTo(Usuario outroUsuario) {
		if (this.getPops() < outroUsuario.getPops())
			return -1;
		else if (this.getPops() > outroUsuario.getPops())
			return 1;
		else if (this.getPops() == outroUsuario.getPops())
			if ((this.getEmail().compareToIgnoreCase(outroUsuario.getEmail())) < 0)
				return -1;
			else if ((this.getEmail().compareToIgnoreCase(outroUsuario.getEmail())) > 0)
				return 1;
		return 0;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
	/**
	 * Metodo que retorna uma lista com todos os hashtags.
	 * 
	 * @return Retorna a lista de hashtags.
	 */
	public List<String> getHashtags() {
		List<String> hashtags = new ArrayList<>();
		for (Post post : mural) {
			hashtags.addAll(post.getListaDeHashtags());
		}
		return hashtags;
	}
}
