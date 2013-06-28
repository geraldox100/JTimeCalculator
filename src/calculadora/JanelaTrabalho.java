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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class JanelaTrabalho implements Janela {
	private Set<Periodo> periodos;

	public JanelaTrabalho() {
		periodos = new HashSet<Periodo>();
	}

	public JanelaTrabalho(int inicial, int finall) {
		this();
		adicionarPeriodo(inicial, finall);
	}

	public Collection<Periodo> getPeriodos() {
		return this.periodos;
	}

	public void adicionarPeriodo(int inicial, int finall) {
		this.periodos.add(new Periodo(inicial, finall));
	}

	@Override
	public boolean estaDentro(DateTime data) {
		if (data.getDayOfWeek() != DateTimeConstants.SATURDAY && data.getDayOfWeek() != DateTimeConstants.SUNDAY) {
			int h = data.getHourOfDay();
			
			for (Periodo p : periodos) {
				if (h >= p.getInicial() && h < p.getFinall()) {
					return true;
				}
			}
		}
		return false;
	}

	private final class Periodo {

		private int inicial;
		private int finall;

		public Periodo(int inicial, int finall) {
			this.inicial = inicial;
			this.finall = finall;
		}

		public int getInicial() {
			return inicial;
		}

		public int getFinall() {
			return finall;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + finall;
			result = prime * result + inicial;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Periodo))
				return false;
			Periodo p = (Periodo) obj;
			return ((this.finall == p.finall) && (this.inicial == p.inicial));
		}

		private Janela getOuterType() {
			return JanelaTrabalho.this;
		}

	}


}
