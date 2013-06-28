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
package calculadora;

import org.joda.time.DateTime;

import org.joda.time.DateTimeConstants;
public class CalculadoraDeHoras {

	public Tempo quantidadeDeHoras(DateTime dataInicial, DateTime dataFinal, Janela janela) {

		long horasTrabalhadas = 0;

		int minutosDaHoraInicial = 0;
		int minutosDaHoraFinal = 0;

		if (forDiferente(dataInicial, dataFinal)) {
			minutosDaHoraInicial = tratarMinutosIniciais(dataInicial, janela);
			minutosDaHoraFinal = tratarMinutosFinais(dataFinal, janela);
		} else {
			minutosDaHoraInicial = dataFinal.getMinuteOfHour();
			minutosDaHoraFinal = dataInicial.getMinuteOfHour() * -1;
		}

		dataInicial = dataInicial.plusMinutes(minutosDaHoraInicial);

		while (forDiferente(dataInicial, dataFinal) && dataInicial.isBefore(dataFinal)) {

			if (janela.estaDentro(dataInicial)) {
				horasTrabalhadas++;
			}

			dataInicial = dataInicial.plusHours(1);

		}

		return criarTempo(horasTrabalhadas, minutosDaHoraInicial, minutosDaHoraFinal);
	}

	private boolean forDiferente(DateTime inicial, DateTime finall) {

		if (inicial.getHourOfDay() != finall.getHourOfDay() && inicial.getHourOfDay() < finall.getHourOfDay())
			return true;
		if (inicial.getDayOfMonth() != finall.getDayOfMonth())
			return true;
		if (inicial.getMonthOfYear() != finall.getMonthOfYear())
			return true;
		if (inicial.getYear() != finall.getYear())
			return true;
		return false;
	}

	private int tratarMinutosFinais(DateTime finall, Janela janela) {
		int minutosFinais = finall.getMinuteOfHour();
		if (!janela.estaDentro(finall)) {
			minutosFinais = 0;
		}
		return minutosFinais;
	}

	private int tratarMinutosIniciais(DateTime inicial, Janela janela) {
		int minutosIniciais = inicial.getMinuteOfHour();
		if (!janela.estaDentro(inicial)) {
			minutosIniciais = 0;
		} else {
			if (minutosIniciais > 0)
				minutosIniciais = 60 - minutosIniciais;
		}
		return minutosIniciais;
	} 

	private Tempo criarTempo(long horas, int minutosDaHoraInicial, int minutosDaHoraFinal) {

		int dias = (int) (horas / 24);

		horas = (int) (horas % 24);
		horas += (int) (minutosDaHoraInicial + minutosDaHoraFinal) / 60;

		int minutos = (int) (minutosDaHoraInicial + minutosDaHoraFinal) % 60;

		return new Tempo(dias, horas, minutos);
	}
}
