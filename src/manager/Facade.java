package manager;

import exceptions.AtualizaPerfilNaoLogado;
import exceptions.AtualizacaoDePerfilException;
import exceptions.CadastroDeUsuarioException;
import exceptions.ConsultaDePopsException;
import exceptions.CriaPostException;
import exceptions.FechaSistemaException;
import exceptions.IndiceConteudoPostInvalido;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NaoHaNotificacoesException;
import exceptions.NaoTemAmizadeException;
import exceptions.NenhumUsuarioLogadoException;
import exceptions.PostTalNaoExisteException;
import exceptions.RequisicaoInvalidaException;
import exceptions.SenhaProtegidaException;
import exceptions.SolicitacaoInexistenteException;
import exceptions.UsuarioAindaLogadoException;
import exceptions.UsuarioNaoCadastradoException;

/**
 * Facade do +Pop
 * 
 * @author matteus
 *
 */
public class Facade {
	private Controller controller;

	/**
	 * Construtor de Facade.
	 */
	public Facade() {
		this.controller = new Controller();
	}

	/**
	 * Cadastra um usuario no +Pop.
	 * 
	 * @param nome
	 *            Nome do usuario a ser cadastrado.
	 * @param email
	 *            Email que vai ser usado para fazer login.
	 * @param senha
	 *            Senha que vai ser usada pra fazer login.
	 * @param dataNasc
	 *            Data de nascimento do usuario a ser cadastrado.
	 * @param imagem
	 *            Imagem de perfil do usuario a ser cadastrado.
	 * @return Retorna o e-mail do usuario que foi cadastrado.
	 * @throws CadastroDeUsuarioException
	 *             Excessao lancada em um dos seguintes casos: nome, e-mail ou
	 *             data sao invalidos, um usuario com o e-mail informado ja
	 *             esta cadastrado.
	 */
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem)
			throws CadastroDeUsuarioException {
		return controller.cadastraUsuario(nome, email, senha, dataNasc, imagem);
	}

	/**
	 * Cadastra um usuario no +Pop.
	 * 
	 * @param nome
	 *            Nome do usuario a ser cadastrado.
	 * @param email
	 *            Email que vai ser usado para fazer login.
	 * @param senha
	 *            Senha que vai ser usada pra fazer login.
	 * @param dataNasc
	 *            Data de nascimento do usuario a ser cadastrado.
	 * @return Retorna o e-mail do usuario que foi cadastrado.
	 * @throws CadastroDeUsuarioException
	 *             Excessao lancada em um dos seguintes casos: nome, e-mail ou
	 *             data sao invalidos, um usuario com o e-mail informado ja
	 *             esta cadastrado.
	 */
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc)
			throws CadastroDeUsuarioException {
		return controller.cadastraUsuario(nome, email, senha, dataNasc, null);
	}

	/**
	 * Metodo que loga um usuario no sistema.
	 * 
	 * @param email
	 *            Email do usuario que esta tentando fazer login.
	 * @param senha
	 *            Senha do usuario que esta tentando fazer login.
	 * @throws LoginException
	 *             Excessao lancada nos seguintes casos: quando um usuario
	 *             nao esta cadastrado, quando a senha e invalida ou quando
	 *             ele ja esta logado.
	 */
	public void login(String email, String senha) throws LoginException {
		controller.login(email, senha);
	}

	/**
	 * Desloga um usuario do sistema.
	 * 
	 * @throws LogoutException
	 *             Excessao lancada quando nao ha usuario para ser
	 *             deslogado.
	 */
	public void logout() throws LogoutException {
		controller.logout();
	}

	/**
	 * Atualiza o perfil de um usuario do +Pop.
	 * 
	 * @param atributo
	 *            Variavel que determina qual sera o atributo de Usuario que
	 *            vai ser atualizado.
	 * @param valor
	 *            Novo valor do atributo a ser atualizado.
	 * @throws AtualizacaoDePerfilException
	 *             Excessao lancada quando o valor nao e valido, ou seja,
	 *             data, nome ou e-mail nao sao validos.
	 * @throws AtualizaPerfilNaoLogado
	 *             Excessao lancada quando nenhum usuario esta logado no
	 *             +Pop.
	 */
	public void atualizaPerfil(String atributo, String valor)
			throws AtualizacaoDePerfilException, AtualizaPerfilNaoLogado {
		controller.atualizaPerfil(atributo, valor);
	}

	/**
	 * Atualiza a senha de um usuario.
	 * 
	 * @param atributo
	 *            ?????????????
	 * @param valor
	 *            Nova senha do usuario.
	 * @param velhaSenha
	 *            Senha antiga.
	 * @throws AtualizacaoDePerfilException
	 *             Excessao lancada se nenhum usuario estiver logado no +Pop
	 *             ou se a senha estiver incorreta.
	 */
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws AtualizacaoDePerfilException {
		controller.atualizaPerfil(atributo, valor, velhaSenha);
	}

	/**
	 * Metodo que retorna uma informacao especifica de um usuario.
	 * 
	 * @param atributo
	 *            String que indica qual atributo o metodo deve retornar.
	 * @param usuario
	 *            E-mail do usuario que tem a informacao.
	 * @return Retorna o atributo requerido no parametro atributo.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessao lancada quando o e-mail passado como parametro
	 *             nao esta cadastrado em nenhum usuario
	 * @throws SenhaProtegidaException
	 *             Excessao lancada quando se tenta acessar a senha de um
	 *             usuario, pois a senha e protegida.
	 */
	public String getInfoUsuario(String atributo, String usuario)
			throws UsuarioNaoCadastradoException, SenhaProtegidaException {
		return controller.getInfoUsuario(atributo, usuario);
	}

	/**
	 * Metodo que retorna uma informacao especifica do usuario que esta
	 * logado no +Pop
	 * 
	 * @param atributo
	 *            String que indica qual o atributo que o metodo deve retornar.
	 * @return Retorna um atributo de um usuario logado no +Pop.
	 * @throws SenhaProtegidaException
	 *             Excessao lancada se o atributo for senha, pois a senha e
	 *             protegida.
	 */
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		return controller.getInfoUsuario(atributo);
	}

	/**
	 * Remove um usuario do +Pop.
	 * 
	 * @param email
	 *            e-mail de cadastro do usuario a ser removido.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessao lancada se nao houver usuario cadastrado com
	 *             esse e-mail.
	 */
	public void removeUsuario(String email) throws UsuarioNaoCadastradoException {
		controller.removeUsuario(email);
	}

	/**
	 * Inicia o +Pop.
	 */
	public void iniciaSistema() {
	}

	/**
	 * Fecha o +Pop.
	 * 
	 * @throws FechaSistemaException
	 *             Excessao lancada quando ainda ha um usuario logado no
	 *             +Pop.
	 */
	public void fechaSistema() throws FechaSistemaException {
		if (controller.isUsuarioLogado())
			throw new FechaSistemaException(new UsuarioAindaLogadoException());
	}
	
	/**
	 * Metodo que cria um Post com uma mensagem e uma data.
	 * 
	 * @param mensagem
	 * 			String que indica qual mensagem sera criada.
	 * @param data
	 * 			String que indica qual a data sera criada.
	 * @throws CriaPostException
	 * 			Excessao lancada quando o post nao for criado.
	 */
	public void criaPost(String mensagem, String data) throws CriaPostException {
		controller.criaPost(mensagem, data);
	}
	
	/**
	 * Metodo que retorna o Post.
	 * 
	 * @param post
	 * 			Inteiro que indica o indice do post.
	 * @return Retorna o Post atraves do metodo.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida.
	 */
	public String getPost(int post) throws RequisicaoInvalidaException {
		return controller.getPost(post);
	}
	
	/**
	 * Retorna o Post a partir dos parametros.
	 * 
	 * @param atributo
	 * 			String que indica o atributo.
	 * @param post
	 * 			Inteiro que indica o indice do Post.
	 * @return Retorna um Post atraves dos parametros.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida.
	 */
	public String getPost(String atributo, int post) throws RequisicaoInvalidaException {
		return controller.getPost(atributo, post);
	}
	
	/**
	 * Retorna o conteudo do post.
	 * 
	 * @param indice
	 * 			Inteiro que indica o conteudo.
	 * @param post
	 * 			Inteiro para indicar o indice do post.
	 * @return	Retorna uma string atraves do metodo que indica o conteudo do post.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida
	 * @throws IndiceConteudoPostInvalido
	 * 			Excessao lancada quando o indice do post eh invalido
	 */
	public String getConteudoPost(int indice, int post) throws RequisicaoInvalidaException, IndiceConteudoPostInvalido {
		return controller.getConteudoPost(indice, post);
	}
	
	/**
	 * Metodo que adiciona um amigo.
	 * 
	 * @param usuario
	 * 			String do Usuario que sera adicionado.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum Usuario esta logado
	 * @throws UsuarioNaoCadastradoException
	 * 			Excessao lancada quando nao existe usuarios cadastrados
	 */
	public void adicionaAmigo(String usuario) throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException {
		controller.adicionaAmigo(usuario);
	}
	
	/**
	 * Metodo que retorna as notificacoes.
	 * 
	 * @return Retorna as notificacoes.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhuma usuario esta logado
	 */
	public int getNotificacoes() throws NenhumUsuarioLogadoException {
		return controller.getNotificacoes();
	}
	
	/**
	 * Retorna a notificacao seguinte.
	 * 
	 * @return Retorna a proxima notificacao atraves do metodo.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 * @throws NaoHaNotificacoesException
	 * 			Excessao lancada quando nao existe notificacoes.
	 */
	public String getNextNotificacao() throws NenhumUsuarioLogadoException, NaoHaNotificacoesException {
		return controller.getNextNotificacao();
	}
	
	/**
	 * Metodo que rejeita a amizade.
	 * 
	 * @param usuario
	 * 			String do usuario que sera rejeitada a amizade.
	 * @throws UsuarioNaoCadastradoException
	 * 			Excessao lancada quando o usuario nao esta cadastrado.
	 * @throws SolicitacaoInexistenteException
	 * 			Excessao lancada quando o usuario nao solicitou amizade.
	 */
	public void rejeitaAmizade(String usuario) throws UsuarioNaoCadastradoException, SolicitacaoInexistenteException {
		controller.rejeitaAmizade(usuario);
	}
	
	/**
	 * Retorna a quantidade de amigos.
	 * 
	 * @return Retorna o metodo que captura a quantidade de amigos.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 */
	public int getQtdAmigos() throws NenhumUsuarioLogadoException {
		return controller.getQtdAmigos();
	}
	
	/**
	 * Metodo que aceita a amizade.
	 * 
	 * @param usuario
	 * 			String do Usuario que indica qual sera aceitado.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 * @throws UsuarioNaoCadastradoException
	 * 			Excessao lancada quando o usuario esta cadastrado.
	 * @throws SolicitacaoInexistenteException
	 * 			Excessao lancada quando o usuario nao soliciou amizade.
	 */
	public void aceitaAmizade(String usuario)
			throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException, SolicitacaoInexistenteException {
		controller.aceitaAmizade(usuario);
	}

	/**
	 * Metodo para curtir um post.
	 * 
	 * @param amigo
	 * 			String do amigo que tera o Post curtido.
	 * @param post
	 * 			Inteiro que indica o indice do Post.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 * @throws UsuarioNaoCadastradoException
	 * 			Excessao lancada quando o usuario esta cadastrado.
	 * @throws NaoTemAmizadeException
	 * 			Excessao lancada quando o usuario nao tem esta amizade.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida.
	 * @throws PostTalNaoExisteException
	 * 			Excessao lancada quando o post nao existe.
	 */
	public void curtirPost(String amigo, int post) throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException,
			NaoTemAmizadeException, RequisicaoInvalidaException, PostTalNaoExisteException {
		controller.curtirPost(amigo, post);
	}
	
	/**
	 * Metodo que rejeita um post.
	 * 
	 * @param amigo
	 * 			String do amigo que tera o post rejeitado.
	 * @param post
	 * 			Inteiro que indica qual post sera rejeitado.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 * @throws UsuarioNaoCadastradoException
	 * 			Excessao lancada quando o usuario nao esta cadastrado.
	 * @throws NaoTemAmizadeException
	 * 			Excessao lancada quando o usuario nao tem esta amizade.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida
	 * @throws PostTalNaoExisteException
	 * 			Excessao lancada quando o post nao existe.
	 */
	public void rejeitarPost(String amigo, int post) throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException,
			NaoTemAmizadeException, RequisicaoInvalidaException, PostTalNaoExisteException {
		controller.rejeitarPost(amigo, post);
	}
	
	/**
	 * Metodo que remove um amigo.
	 * 
	 * @param usuario
	 * 			String do usuario que sera removido.
	 * @throws UsuarioNaoCadastradoException
	 * 			Excessao lancada quando o usuario nao esta cadastrado.
	 * @throws NaoTemAmizadeException
	 * 			Excessao lancada quando nao tem esta amizade.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 */
	public void removeAmigo(String usuario)
			throws UsuarioNaoCadastradoException, NaoTemAmizadeException, NenhumUsuarioLogadoException {
		controller.removeAmigo(usuario);
	}

	/**
	 * Metodo que adiciona Pops
	 * 
	 * @param pops
	 * 			Inteiro que indica a quantidade de pops que sera adicionada.
	 */
	public void adicionaPops(int pops) {
		controller.adicionaPops(pops);
	}
	
	/**
	 * Retorna a popularidade.
	 * 
	 * @return Retorna a popularidade atraves do metodo
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 */
	public String getPopularidade() throws NenhumUsuarioLogadoException {
		return controller.getPopularidade();
	}
	
	/**
	 * Metodo que retorna a quantidade de pops em um determinado post.
	 * 
	 * @param post
	 * 			Inteiro que indica o post que sera analisado.
	 * @return Retorna a quantidade de pops do post através do metodo.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida
	 * @throws PostTalNaoExisteException
	 * 			Excessao lancada quando o post nao existe.
	 */
	public int getPopsPost(int post)
			throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		return controller.getPopsPost(post);
	}
	
	/**
	 * Metodo que retorna a quantidade de curtidas de um determinado Post.
	 * 
	 * @param post
	 * 			Inteiro que indica o indice do post.
	 * @return Retorna a quantidade de curtidas atraves do metodo
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida
	 * @throws PostTalNaoExisteException
	 * 			Excessao lancada quando o post nao existe.
	 */
	public int qtdCurtidasDePost(int post)
			throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		return controller.qtdCurtidasDePost(post);
	}
	
	/**
	 * Metodo que retorna a quantidade de rejeicoes de um Post.
	 * 
	 * @param post
	 * 			Inteiro que indica qual o post sera analisado.
	 * @return Retorna a quantidade de rejeicoes de um post pelo metodo.
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 * @throws RequisicaoInvalidaException
	 * 			Excessao lancada quando a requisicao eh invalida
	 * @throws PostTalNaoExisteException
	 * 			Excessao lancada quando o post nao existe.
	 */
	public int qtdRejeicoesDePost(int post)
			throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		return controller.qtdRejeicoesDePost(post);
	}
	
	/**
	 * Metodo que retorna a popularidade de um Usuario
	 * 
	 * @param usuario
	 * 			String do usario que tera sua popularidade retornada.
	 * @return Retorna a popularidade do usuario atraves do metodo.
	 * @throws ConsultaDePopsException
	 * 			Excessao lancada quando da erro na consulta de pops
	 * @throws UsuarioNaoCadastradoException
	 * 			Excessao lancada quando o usuario nao esta cadastrado.
	 */
	public int getPopsUsuario(String usuario) throws ConsultaDePopsException, UsuarioNaoCadastradoException {
		return controller.getPopsUsuario(usuario);
	}
	
	/**
	 * Metodo que retorna a Popularidade.
	 * 
	 * @return Retorna a popularidade pelo metodo
	 * @throws NenhumUsuarioLogadoException
	 * 			Excessao lancada quando nenhum usuario esta logado.
	 */
	public int getPopsUsuario() throws NenhumUsuarioLogadoException {
		return controller.getPopsUsuario();
	}
	
	/**
	 * Metodo que atualiza o Ranking
	 * 
	 * @return Retorna o metodo para atualizar o ranking
	 */
	public String atualizaRanking() {
		return controller.atualizaRanking();
	}
	
	/**
	 * Metodo que atualiza o Trending Topics.
	 * 
	 * @return Retorna o metodo para atualizar o Trending Topics.
	 */
	public String atualizaTrendingTopics() {
		return controller.atualizaTrendingTopics();
	}
}
