package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

@SuppressWarnings("deprecation")
public class GerenciadorDeSessaoTest {

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
		Filme filme = new Filme("Rouge One", Duration.ofMinutes(120), "Sci-fi",BigDecimal.ONE);
		LocalTime horaLocalTime = LocalTime.parse("10:00:00");

		Sala sala = new Sala("",BigDecimal.ONE);
		List<Sessao> sessoes = Arrays.asList(new Sessao(horaLocalTime, filme,
				sala));

		Sessao sessao = new Sessao(horaLocalTime, filme, sala);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {

		Filme filme = new Filme("Rouge One", Duration.ofMinutes(120), "Sci-fi",BigDecimal.ONE);
		LocalTime horaLocalTime = LocalTime.parse("10:00:00");

		Sala sala = new Sala("",BigDecimal.ONE);
		List<Sessao> sessoes = Arrays.asList(new Sessao(horaLocalTime, filme,
				sala));

		Sessao sessao = new Sessao(horaLocalTime.minusHours(1), filme, sala);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gerenciador.cabe(sessao));

	}

	@Test
	public void garanteQueNaoDevePermitirSessoesIniciadoDentroDoHorarioDeUmaSessaoJaExistentr() {

		Filme filme = new Filme("Rouge One", Duration.ofMinutes(120), "Sci-fi",BigDecimal.ONE);
		LocalTime horaLocalTime = LocalTime.parse("10:00:00");

		Sala sala = new Sala("",BigDecimal.ONE);
		List<Sessao> sessoes = Arrays.asList(new Sessao(horaLocalTime, filme,
				sala));

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gerenciador.cabe(new Sessao(horaLocalTime
				.plusHours(1), filme, sala)));
	}

	@Test
	public void garanteQueNaoDevePermitirUmaInsercaoEntreDoisFilmes() {
		Sala sala = new Sala("",BigDecimal.ONE);

		Filme filme1 = new Filme("Rog", Duration.ofMinutes(90), "sifi",BigDecimal.ONE);

		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(dezHoras, filme1, sala);

		Filme filme2 = new Filme("Rog2", Duration.ofMinutes(120), "sifi",BigDecimal.ONE);

		LocalTime dezoitoHoras = LocalTime.parse("18:00:00");
		Sessao sessaoDasDezoitos = new Sessao(dezoitoHoras, filme2, sala);

		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoitos);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Assert.assertTrue(gerenciador.cabe(new Sessao(LocalTime
				.parse("13:00:00"), filme2, sala)));

	}
	
	@Test
	public void precoDaSessaoDeveSeraSomaDaSalaComFilme(){
		Sala sala = new Sala("",new BigDecimal("22.5"));
		Filme filme = new Filme("Roug",Duration.ofMinutes(120),"sifi",new BigDecimal("12.0"));
		BigDecimal somaDosPrecos = filme.getPreco().add(sala.getPreco());
		
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"),filme,sala);
		
		Assert.assertEquals(somaDosPrecos,sessao.getPreco());
	}
}
