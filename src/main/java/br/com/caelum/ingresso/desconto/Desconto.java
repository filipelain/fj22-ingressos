package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public interface Desconto {
	BigDecimal apicarDescontoSobre(BigDecimal precoOriginal);
}
