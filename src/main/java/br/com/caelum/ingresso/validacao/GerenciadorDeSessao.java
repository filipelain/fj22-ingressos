package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	private List<Sessao> sessoesDaSala;

	public GerenciadorDeSessao(List<Sessao> sessoesDaSaça) {
		this.sessoesDaSala = sessoesDaSaça;
	}

	public boolean cabe(final Sessao sessaoAtual) {

		Optional<Boolean> optionalCabe = sessoesDaSala
				.stream()
				.map(sessaoExiste -> horariosIsValido(sessaoExiste, sessaoAtual))
				.reduce(Boolean::logicalAnd);

		return optionalCabe.orElse(true);
	}

	private boolean horariosIsValido(Sessao sessaoExiste, Sessao sessaoAtual) {

		LocalDate hoje = LocalDate.now();

		LocalDateTime horarioSessao = sessaoExiste.getHorario().atDate(hoje);
		LocalDateTime horatioAtual = sessaoAtual.getHorario().atDate(hoje);
		boolean ehAntes = horatioAtual.isBefore(horarioSessao);

		if (ehAntes) {
			return horatioAtual.plus(sessaoAtual.getFilme().getDuracao())
					.isBefore(horarioSessao);
		} else {
			return horarioSessao.plus(sessaoExiste.getFilme().getDuracao())
					.isBefore(horatioAtual);
		}

	}
}
