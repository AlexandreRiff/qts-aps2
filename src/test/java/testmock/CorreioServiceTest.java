package testmock;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import junit.framework.Assert;
import mock.CorreioService;

public class CorreioServiceTest {

	@Test
	public void testBuscaEndereco() {
		CorreioService correioService = Mockito.mock(CorreioService.class);

		when(correioService.buscaEndereco("9000000")).thenReturn("Porto Alegre");
		String resultadoExperado = "Porto Alegre";
		String resultadoPrograma = correioService.buscaEndereco("9000000");
		Assert.assertEquals(resultadoExperado, resultadoPrograma);

		when(correioService.buscaEndereco("8000000")).thenReturn("Av. Assis Brasil");
		resultadoExperado = "Av. Assis Brasil";
		resultadoPrograma = correioService.buscaEndereco("8000000");
		Assert.assertEquals(resultadoExperado, resultadoPrograma);
	}

}
