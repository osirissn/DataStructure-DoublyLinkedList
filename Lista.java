 // CLASSE LISTA DUPLAMENTE ENCADEADA
public class LDE {

    public DNode inicio;
    public DNode fim;
    public int tamanho;

    public LDE() {
        inicio = fim = null;
        tamanho = 0;
    }

    public int size() {

        return tamanho;
    }

    public boolean isEmpty() {

        return tamanho == 0;

    }
 // CLASSE LISTA DUPLAMENTE ENCADEADA - MÉTODOS
    public void inserir_pos(int pos, Object v) {
        if (pos < 0 || pos > tamanho) {
            System.out.println("POSICAO INVALIDA!!!!");
            return;
        }
        if (pos == 0) {
            inserirPrimeiro(v);
        } else if (pos == tamanho) {
            inserirUltimo(v);
        } else {
            DNode aux = inicio;
            for (int cont = 0; cont < pos - 1; cont++) {
                aux = aux.getNext();
            }
            DNode novoNo = new DNode(v, aux, aux.getNext());
            aux.getNext().setPrev(novoNo);
            aux.setNext(novoNo);
            tamanho++;
        }

    }

    public Object removerPrimeiro() {
        if (!isEmpty()) {
            Object retorno = inicio.getElement();
            inicio = inicio.getNext();
            tamanho--;
            if (isEmpty()) {
                fim = null;
            } else {
                inicio.setPrev(null);
            }
            return retorno;
        } else {
            return null;
        }
    }


    public void inserirPrimeiro(Object v) {
        DNode novoNo = new DNode(v, null, inicio);
        if (inicio != null) {
            inicio.setPrev(novoNo);
        } else {
            fim = novoNo;
        }
        inicio = novoNo;
        tamanho++;
    }

    // remover ultimo
    public Object removerUltimo() {
        if (!isEmpty()) {
            Object retorno = fim.getElement();
            fim = fim.getPrev();
            tamanho--;
            if (isEmpty()) {
                inicio = null;
            } else {
                fim.setNext(null);
            }
            return retorno;
        } else {
            return null;
        }
    }

    // inserir ultimo
    public void inserirUltimo(Object v) {
        DNode novoNo = new DNode(v, fim, null);
        if (isEmpty()) {
            inicio = novoNo;
        } else {
            fim.setNext(novoNo);
        }
        fim = novoNo;
        tamanho++;
    }

    public void imprimir() {
        if (isEmpty()) {
            System.out.println("Esta vazia");
        } else {
            DNode aux = inicio;
            while (aux != null) {
                System.out.print(aux.getElement() + " ");
                aux = aux.getNext();
            }
        }

        System.out.println();
    }
/* 1) Remover Elemento do Meio - Adaptar o código para quando tiver apenas 1 elemento . Remover o elemento do
        meio da lista, supondo uma lista com número de elementos ímpares.*/
             public DNode removerMeio() {
        if (isEmpty()) {
            return null;
        } else if (size() == 1) {
            return inicio;
        } else {
            int meio = size() / 2;
            int cont = 0;
            DNode aux = inicio;
            while (aux != null) {

                if (cont == meio) {
                    DNode ant = aux.getPrev();
                    DNode prox = aux.getNext();
                    ant.setNext(prox);
                    aux.setNext(null);
                    prox.setPrev(ant);
                    aux.setPrev(null);
                    return aux;
                }
                aux = aux.getNext();
                cont++;
            }
            return null;
        }
    }
/* 2) Buscar elemento - Implementar o método de busca retornar -1 caso não encontre ou posição se encontrar o elemento. */
    public int buscar(Integer no) {
        if (isEmpty()) {
            return -1;
        } else {
            int cont = 0;
            DNode aux = inicio;
            while (aux != null) {
                if (aux.getElement() == no) {
                    return cont;
                }
                aux = aux.getNext();
                cont++;
            }
            return -1;
        }
    }
//    3) Verificar se existe um elemento na lista

    public boolean verificar(Integer no) {
        if (isEmpty()) {
            return false;
        } else {
            int cont = 0;
            DNode aux = inicio;
            while (aux != null) {
                if (aux.getElement() == no) {
                    return true;
                }
                aux = aux.getNext();
                cont++;
            }
            return false;
        }
    }

    //    4) Inserir no meio; (4 elementos - inserir depois do segundo)
    public void inserirNoMeio(Integer valor) {

        if (isEmpty()) {
            inserirPrimeiro(valor);
        } else {
            int meio = size() / 2;
            DNode aux = inicio;
            int cont = 0;
            while (aux != null) {
                if (cont == meio) {
                    DNode ant = aux.getPrev();
                    DNode novo = new DNode(valor, ant, aux);
                    ant.setNext(novo);
                    novo.setNext(aux);
                    novo.setPrev(novo);
                    aux.setPrev(novo);
                    novo.setPrev(ant);

                }
                aux = aux.getNext();
                cont++;
            }
        }
    }


//    5) Verificar se a LDE tem elementos repetidos

    public boolean temRepetido() {
        DNode aux1 = inicio;
        while (aux1 != null) {
            DNode aux2 = inicio;
            while (aux2 != aux1) {
                if (aux2.getElement() == aux1.getElement()) {
                    return true;
                }
                aux2 = aux2.getNext();
            }
            aux1 = aux1.getNext();
        }
        return false;
    }


            //    6) Remover elementos repetidos da LDE
    public void removerRepetidos() {
         if (inicio == null) {
        return;
    }
         DNode aux = inicio;

         while (aux != null) {
        Object valorAtual = aux.getElement();


             DNode aux2 = aux.getNext();
             while (aux2 != null) {
            if (aux2.getElement() == valorAtual) {

                DNode anterior = aux2.getPrev();
                DNode proximo = aux2.getNext();
                anterior.setNext(proximo);

                if (proximo != null) {
                    proximo.setPrev(anterior);
                }
            }

            aux2 = aux2.getNext();
        }

        aux = aux.getNext();
    }
}

//    7) Supondo que a LDE tem elementos repetidos verificar qual é o elemento que mais se repete
    // questao dificil da porra

    public Object elementoQueMaisRepete() {
    if (inicio == null) {
        System.out.println("Lista ta vazia, chefe");
        return 0;
    }

    Object elementoMaisFrequente = 0;
    int frequenciaMaxima = 0;

    DNode aux1 = inicio;

    while (aux1 != null) {
        Object valor = aux1.getElement();
        int frequencia = 0;

        DNode aux2 = inicio;

        while (aux2 != null) {
            if (aux2.getElement() == valor) {
                frequencia++;
            }
            aux2 = aux2.getNext();
        }
        if (frequencia > frequenciaMaxima) {
            frequenciaMaxima = frequencia;
            elementoMaisFrequente = valor;
        }
        aux1 = aux1.getNext();
    }
    return elementoMaisFrequente;
}








 
    // playlist -> https://www.youtube.com/watch?v=vtxCkhe7l0U&list=PLt4nG7RVVk1gIcVQAo8laecQWkzOdYe6i&index=4

    // inserir numa posicao dada
    public void adicionarNaPosicao(int pos, DNode no) {
        if (isEmpty()) {
            System.out.println("Esta vazia, vou inserir na primeira posicao");

            inserirUltimo(no);
        }
        if (pos < 0 || pos > size()) {
            System.out.println("Posicao invalida, meu fi, tem isso tudo de nó nao");
        } else {
            DNode aux = inicio;
            int cont = 0;
            while (aux != null) {
                if (cont == pos) {
                    DNode ant = aux.getPrev();
                    no.setPrev(ant);
                    no.setNext(aux);
                    ant.setNext(no);
                    aux.setPrev(aux);
                }
                aux = aux.getNext();
                cont++;
            }
        }
    }


    // remover o primeiro no da lista de outro jeito
    public DNode removeFirst() {
        if (isEmpty()) {
            System.out.println("Esta vazia, nao tem o que remover");
            return null;
        } else if (inicio.getNext() != null) {

            inicio.getNext().setPrev(null);
        }
        return inicio;
    }
        // remover um No em uma posicao dada
    public DNode removerNaPosicao(int pos) {
        if (isEmpty()) {
            System.out.println("Esta vazia, nao tem o que remover");
        }
        if( pos == tamanho-1){
            return fim;
        }
        if( pos == 0){
            return inicio;
        }
        if (pos < 0 || pos > tamanho) {
            System.out.println("Posicao invalida, meu fi,");
        } else {
            DNode aux = inicio;
            int cont = 0;
            while (aux != null) {
                if (cont == pos) {
                    aux.getPrev().setNext(aux.getNext());
                    aux.getNext().setPrev(aux.getPrev());
//                    DNode ant = aux.getPrev();
//                    DNode post = aux.getNext();
//                    ant.setNext(post);
//                    post.setPrev(ant);
                    return aux;
                }
                aux = aux.getNext();
                cont++;
            }
        }
        return null;
    }
}
/*	Lista Encadeada:


	


public static void main(String[] args) {
 while (aux1 != null) {
                        boolean temRepetido = false;
                        DNode aux3 = lista3.inicio;
                        while (aux3 != null) {
                                if (aux3.getElement() == aux1.getElement()) {
                                        temRepetido = true;

                                }
                                aux3 = aux3.getNext();
                        }
                        if (!temRepetido) {
                                lista3.inserirUltimo(aux1.getElement());
                        }
                        aux1 = aux1.getNext();
                }

                DNode aux2 = lista2.inicio;

                while (aux2 != null) {
                        boolean temRepetido = false;
                        DNode aux3 = lista3.inicio;
                        while (aux3 != null) {
                                if (aux3.getElement() == aux2.getElement()) {
                                        temRepetido = true;
                                        
                                }
                                aux3 = aux3.getNext();
                        }
                        if (!temRepetido) {
                                lista3.inserirUltimo(aux2.getElement());
                        }
                        aux2 = aux2.getNext();
                }
		lista3.imprimir();
}

}


 

















