/*
 * Copyright 2010-2013 Geraldo Ferraz de Oliveira Filho
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */
package teste;

import static junit.framework.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import calculadora.CalculadoraDeHoras;
import calculadora.Janela;
import calculadora.JanelaTrabalho;
import calculadora.Tempo;

public class CalculadoraDeHorasTest {

	private DateTime inicial;
	private DateTime finall;
	private JanelaTrabalho janela;
	private CalculadoraDeHoras calc;

	@Before
	public void montaObjetos() {
		janela = new JanelaTrabalho();
		janela.adicionarPeriodo(8, 12);
		janela.adicionarPeriodo(14, 18);

		calc = new CalculadoraDeHoras();
	}

	@Test
	public void cenarioIdeal() {
		inicial = new DateTime(2010, 07, 16, 8, 0, 0, 0);
		finall = new DateTime(2010, 07, 16, 18, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(8, labor.getHoras());
		assertEquals(0, labor.getMinutos());
	}

	@Test
	public void manhaQuebrada() {
		inicial = new DateTime(2010, 07, 16, 10, 30, 0, 0);
		finall = new DateTime(2010, 07, 16, 18, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 5);
		assertEquals(labor.getMinutos(), 30);
	}

	@Test
	public void tardeQuebrada() {
		inicial = new DateTime(2010, 07, 16, 14, 0, 0, 0);
		finall = new DateTime(2010, 07, 16, 17, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 3);
		assertEquals(labor.getMinutos(), 0);
	}

	@Test
	public void manhaETardeQuebrada() {
		inicial = new DateTime(2010, 07, 16, 9, 0, 0, 0);
		finall = new DateTime(2010, 07, 16, 17, 0, 0, 0);
		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 6);
		assertEquals(labor.getMinutos(), 0);
	}

	@Test
	public void finalDaManhaInicioDaTarde() {
		inicial = new DateTime(2010, 07, 16, 11, 50, 0, 0);
		finall = new DateTime(2010, 07, 16, 13, 55, 0, 0);
		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 0);
		assertEquals(labor.getMinutos(), 10);
	}

	@Test
	public void finalDaTardeAposJanela() {
		inicial = new DateTime(2010, 07, 16, 14, 45, 0, 0);
		finall = new DateTime(2010, 07, 16, 18, 55, 0, 0);
		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 3);
		assertEquals(labor.getMinutos(), 15);
	}

	@Test
	// 123
	public void inicioDaTardeAposJanela() {
		inicial = new DateTime(2010, 07, 16, 13, 15, 0, 0);
		finall = new DateTime(2010, 07, 16, 18, 55, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 4);
		assertEquals(labor.getMinutos(), 0);
	}

	@Test
	public void cenarioIdealEntre2Dias() {
		inicial = new DateTime(2010, 07, 15, 8, 0, 0, 0);
		finall = new DateTime(2010, 07, 16, 18, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 16);
		assertEquals(labor.getMinutos(), 0);
	}

	@Test
	public void antesDaJanelaEntre2Dias() {
		inicial = new DateTime(2010, 07, 15, 7, 45, 0, 0);
		finall = new DateTime(2010, 07, 16, 18, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 16);
		assertEquals(labor.getMinutos(), 0);
	}

	@Test
	public void depoisDaJanelaEntre2Dias() {
		inicial = new DateTime(2010, 07, 15, 18, 45, 0, 0);
		finall = new DateTime(2010, 07, 16, 18, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 8);
		assertEquals(labor.getMinutos(), 0);
	}

	@Test
	public void entre2Dias() {
		inicial = new DateTime(2010, 07, 15, 11, 45, 0, 0);
		finall = new DateTime(2010, 07, 16, 14, 15, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(labor.getHoras(), 8);
		assertEquals(labor.getMinutos(), 30);
	}

	@Test
	public void entre5Dias() {
		inicial = new DateTime(2010, 07, 11, 11, 1, 0, 0);
		finall = new DateTime(2010, 07, 19, 14, 59, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(1, labor.getDias());
		assertEquals(20, labor.getHoras());
		assertEquals(59, labor.getMinutos());
	}

	@Test
	public void entre20Dias() {

		inicial = new DateTime(2010, 07, 1, 8, 0, 0, 0);
		finall = new DateTime(2010, 07, 21, 18, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);

		assertEquals(5, labor.getDias());
		assertEquals(0, labor.getHoras());
		assertEquals(0, labor.getMinutos());
	}

	@Test
	public void cenarioIdeal2() {

		inicial = new DateTime(2010, 7, 12, 17, 51, 0, 0);
		finall = new DateTime(2010, 7, 13, 11, 51, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(4, labor.getHoras());
		assertEquals(0, labor.getMinutos());

	}

	@Test
	public void cenarioIdeal3() {

		inicial = new DateTime(2010, 7, 12, 17, 50, 0, 0);
		finall = new DateTime(2010, 7, 13, 11, 51, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(4, labor.getHoras());
		assertEquals(1, labor.getMinutos());

	}

	@Test
	public void entre5Dias2() {
		inicial = new DateTime(2010, 07, 19, 11, 1, 0, 0);
		finall = new DateTime(2010, 07, 20, 14, 59, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(9, labor.getHoras());
		assertEquals(58, labor.getMinutos());
	}

	@Test
	public void entreAnos() {
		inicial = new DateTime(2009, 12, 31, 14, 0, 0, 0);
		finall = new DateTime(2010, 1, 1, 12, 0, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(8, labor.getHoras());
		assertEquals(0, labor.getMinutos());
	}

	@Test
	public void entreFinaisDeSemana() {
		inicial = new DateTime(2010, 7, 9, 20, 0, 0, 0);
		finall = new DateTime(2010, 7, 12, 8, 1, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
		assertEquals(0, labor.getHoras());
		assertEquals(1, labor.getMinutos());
	}

	@Test
	public void testezao01() {
		inicial = new DateTime(2012, 6, 4, 11, 35, 0, 0);
		finall = new DateTime(2012, 6, 4, 11, 37, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);

		assertEquals(0, labor.getHoras());
		assertEquals(2, labor.getMinutos());
	}

	@Test
	public void testezao02() {

		inicial = new DateTime(2012, 6, 4, 8, 58, 0, 0);
		finall = new DateTime(2012, 6, 14, 14, 35, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);

		assertEquals(2, labor.getDias());
		assertEquals(19, labor.getHoras());
		assertEquals(37, labor.getMinutos());
	}

	@Test
	public void testezao03() {

		inicial = new DateTime(2012, 6, 4, 8, 58, 0, 0);
		finall = new DateTime(2012, 6, 4, 8, 58, 0, 0);

		Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);

		assertEquals(0, labor.getDias());
		assertEquals(0, labor.getHoras());
		assertEquals(0, labor.getMinutos());
	}

}
