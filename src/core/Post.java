package core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import exceptions.IndiceConteudoPostInvalido;

public class Post {
	private List<String> conteudo;
	private List<String> hashTags;
	private LocalDateTime data;
	private int popularidade;
	private int curtidas;
	private int rejeicoes;
	
	public Post(List<String> conteudo, List<String> hashTags, LocalDateTime data) {
		this.conteudo = conteudo;
		this.hashTags = hashTags;
		this.data = data;
		this.popularidade = 0;
		this.curtidas = 0;
		this.rejeicoes = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(passaConteudoPraString());
		sb.append(" ");
		for (String ht : hashTags) {
			sb.append(ht);
			sb.append(" ");
		}
		sb.append(passaDataPraString());
		
		return sb.toString().trim();
	}
	
	private String passaDataPraString() {
		String stringDaData = "(" + getData() + ")";
		return stringDaData;
	}
	
	public String passaConteudoPraString() {
		StringBuilder sb = new StringBuilder();
		sb.append(conteudo.get(0));
		for (int i = 1; conteudo.size() > i; i++) {
			sb.append(conteudo.get(i));
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	public String getHashtags() {
		StringBuilder sb = new StringBuilder();
		sb.append(hashTags.get(0));
		for (int i = 1; hashTags.size() > i; i++){
			sb.append(",");
			sb.append(hashTags.get(i));
		}
		return sb.toString();
	}

	public String getData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String stringDaData = data.format(formatter);
		return stringDaData;
	}

	public String getConteudo(int indice) throws IndiceConteudoPostInvalido {
		if (indice >= conteudo.size())
			throw new IndiceConteudoPostInvalido(indice, conteudo.size());
		String saida = conteudo.get(indice).trim();
		int tamanhoDaSaida = saida.length();
		String complementoAudio = "$arquivo_audio:";
		String complementoImagem = "$arquivo_imagem:";
		if (saida.startsWith("<imagem>"))
			return complementoImagem + saida.substring(8, tamanhoDaSaida - 9);
		if (saida.startsWith("<audio>"))
			return complementoAudio + saida.substring(7, tamanhoDaSaida - 8);
		return saida;
	}
	
	public void adicionaCurtida() {curtidas++;}
	
	public void adicionaRejeicao() {rejeicoes++;}
	
	public void adicionaPopularidade(int valor) {popularidade += valor;}
	
	public void removePopularidade(int valor) {popularidade -= valor;}

	public List<String> getListaDeHashtags() {
		return this.hashTags;
	}

	public void adicionaHashtag(String novaHashtag) {
		hashTags.add(novaHashtag);
	}
	
	public boolean isRecente() {
		LocalDateTime agora = LocalDateTime.now();
		if (agora.getYear() == this.data.getYear()) {
			if (agora.getMonth() == this.data.getMonth()) {
				if (agora.getDayOfMonth() == this.data.getDayOfMonth())
					return true;
			}
		}
		return false;
	}

	public int getPopularidade() {
		return this.popularidade;
	}

	public int getCurtidas() {
		return this.curtidas;
	}

	public int getRejeicoes() {
		return this.rejeicoes;
	}
}
