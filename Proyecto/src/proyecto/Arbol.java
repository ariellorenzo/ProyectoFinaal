
package proyecto;


public class Arbol {
    
    
    private NodoDelArbol raizPrincipal;

    public Arbol() { 
        this.raizPrincipal = null;
    }

    public void insertar(int elemento) { 
        NodoDelArbol actual = raizPrincipal;
        if (this.raizPrincipal == null) {
            this.raizPrincipal = new NodoDelArbol(elemento);
        } else {
            insertarAhora(actual, elemento);
        }
    }

    private void insertarAhora(NodoDelArbol aux, int nuevoDato) {   
        if (nuevoDato > aux.getValorDelNodo()) {

            if (aux.getNodoDerecho() == null) {
                NodoDelArbol nuevo = new NodoDelArbol(nuevoDato);
                aux.setNodoDerecho(nuevo);
            }
            aux = aux.getNodoDerecho();
            insertarAhora(aux, nuevoDato);

        } else if (nuevoDato < aux.getValorDelNodo()) {
            if (aux.getNodoIzquierdo() == null) {
                NodoDelArbol nuevo = new NodoDelArbol(nuevoDato);
                aux.setNodoIzquierdo(nuevo);
            }
            aux = aux.getNodoIzquierdo();
            insertarAhora(aux, nuevoDato);
        }

    }

    public boolean estarVacío() { 
        return (this.raizPrincipal == null);
    }

    public void imprimePre() { 
        preOrden(this.raizPrincipal);
    }

    private void preOrden(NodoDelArbol nodo) {
        if (nodo != null) {
            System.out.print(nodo.getValorDelNodo() + ",");
            preOrden(nodo.getNodoIzquierdo());
            preOrden(nodo.getNodoDerecho());
            System.out.print(" ");
        }
    }

    public void imprimeIno() { 
        inorden(this.raizPrincipal);
        System.out.print(" ");
    }

    private void inorden(NodoDelArbol nodo) {
        if (nodo != null) {
            inorden(nodo.getNodoIzquierdo());
            System.out.print(nodo.getValorDelNodo() + ",");
            inorden(nodo.getNodoDerecho());
        }
    }

    public void imprimePos() {
        postorden(this.raizPrincipal);
        System.out.print("  ");
    }

    private void postorden(NodoDelArbol nodo) {
        if (nodo != null) {
            postorden(nodo.getNodoIzquierdo());
            postorden(nodo.getNodoDerecho());
            System.out.print(nodo.getValorDelNodo() + ",");
        }
    }

    public void buscaHijoDer(int valorDelNodo) { //Método que busca y muestra el hijo o nodo derecho de un nodo específico
        NodoDelArbol recorrido = this.raizPrincipal;
        int valorHijoDerecho = 0;
        boolean encontrado = false;
        if (recorrido != null && existe(valorDelNodo) == true) {
            while (recorrido != null) {
                if (valorDelNodo == recorrido.getValorDelNodo()) {
                    if (recorrido.getNodoDerecho() == null) {
                        System.out.println(" ");
                        encontrado = true;
                        break;
                    } else {
                        valorHijoDerecho = recorrido.getNodoDerecho().getValorDelNodo();
                        System.out.println(valorHijoDerecho);
                        encontrado = true;
                        break;
                    }
                } else if (valorDelNodo > recorrido.getValorDelNodo()) {
                    recorrido = recorrido.getNodoDerecho();
                } else if (valorDelNodo < recorrido.getValorDelNodo()) {
                    recorrido = recorrido.getNodoIzquierdo();
                } else {
                    break;
                }
            }
            if (encontrado == false) {
                System.out.println(" ");
            }
        } else {
            System.out.println(" ");
        }
    }

    public void buscaHijoIzq(int valorDelNodo) { 
        NodoDelArbol recorrido = this.raizPrincipal;
        int valorHijoIzquierdo = 0;
        boolean encontrado = false;
        if (recorrido != null && existe(valorDelNodo) == true) {
            while (recorrido != null) {
                if (valorDelNodo == recorrido.getValorDelNodo()) {
                    if (recorrido.getNodoIzquierdo() == null) {
                        System.out.println(" ");
                        encontrado = true;
                        break;
                    } else {
                        valorHijoIzquierdo = recorrido.getNodoIzquierdo().getValorDelNodo();
                        System.out.println(valorHijoIzquierdo);
                        encontrado = true;
                        break;
                    }
                } 
                else if (valorDelNodo > recorrido.getValorDelNodo()) {
                    recorrido = recorrido.getNodoDerecho();
                } 
                else if (valorDelNodo < recorrido.getValorDelNodo()) {
                    recorrido = recorrido.getNodoIzquierdo();
                } 
                else {
                    break;
                }
            }
            if (encontrado == false) {
                System.out.println(" ");
            }
        } else {
            System.out.println(" ");
        }
    }

    public void elimina(int valor) {  
        NodoDelArbol recorrido = this.raizPrincipal;
        if (recorrido != null && existe(valor) == true) {
            eliminador(recorrido, valor);
        }
    }

    private void eliminador(NodoDelArbol nodo, int valor) {
        if (esHoja(valor) == true) {  
            NodoDelArbol padre = obtenerPadre(valor);
            if (padre == null) {
                this.raizPrincipal = null;
            } else {
                if (padre.getNodoDerecho()!=null && padre.getNodoDerecho().getValorDelNodo() == valor)
                {
                    padre.setNodoDerecho(null);
                } else if (padre.getNodoIzquierdo()!=null && padre.getNodoIzquierdo().getValorDelNodo() == valor) {
                    padre.setNodoIzquierdo(null);
                }
            }
        } else {

            if (nodo.getValorDelNodo() == valor) {
                if (nodo.getNodoIzquierdo() == null && nodo.getNodoDerecho() != null) { 
                    NodoDelArbol padre = obtenerPadre(valor);
                    if (padre == null) {
                        this.raizPrincipal = nodo.getNodoDerecho();
                    } else {
                        if (padre.getNodoDerecho().getValorDelNodo() == valor) {
                            padre.setNodoDerecho(nodo.getNodoDerecho());
                            nodo.setNodoDerecho(null);
                        } else if (padre.getNodoIzquierdo().getValorDelNodo() == valor) {
                            padre.setNodoIzquierdo(nodo.getNodoDerecho());
                            nodo.setNodoDerecho(null);
                        }
                    }
                } else if (nodo.getNodoDerecho() == null && nodo.getNodoIzquierdo() != null) {
                    NodoDelArbol padre = obtenerPadre(valor);
                    if (padre == null) {
                        this.raizPrincipal = nodo.getNodoIzquierdo();
                    } else {
                        if (padre.getNodoDerecho().getValorDelNodo() == valor) {
                            padre.setNodoDerecho(nodo.getNodoIzquierdo());
                            nodo.setNodoIzquierdo(null);
                        } else if (padre.getNodoIzquierdo().getValorDelNodo() == valor) {
                            padre.setNodoIzquierdo(nodo.getNodoIzquierdo());
                            nodo.setNodoIzquierdo(null);
                        }
                    }
                } else if (nodo.getNodoIzquierdo() != null && nodo.getNodoIzquierdo() != null) { //Aquí verificamos si el nodo contiene dos subárboles o nodos hijos
                    NodoDelArbol mayor = recorrerSubIzq(nodo.getNodoDerecho());
                    int valorAuxiliar = mayor.getValorDelNodo();
                    eliminador(nodo.getNodoDerecho(), mayor.getValorDelNodo());
                    nodo.setValorDelNodo(valorAuxiliar);
                }
            } else if (valor > nodo.getValorDelNodo() && nodo.getNodoDerecho()!=null) {
                eliminador(nodo.getNodoDerecho(), valor);
            } else if (valor < nodo.getValorDelNodo() && nodo.getNodoIzquierdo()!=null) {
                eliminador(nodo.getNodoIzquierdo(), valor);
            }
        }

    }

    private NodoDelArbol recorrerSubIzq(NodoDelArbol nodo) { 
        if (nodo.getNodoIzquierdo() != null) {
            return recorrerSubIzq(nodo.getNodoIzquierdo());
        }
        return nodo;
    }

    public boolean existe(int valorDelNodo) { 
        NodoDelArbol recorrido = this.raizPrincipal;
        if (recorrido != null){
        return (existe(valorDelNodo, recorrido));
        }else {
        return false;
        }
    }

    private boolean existe(int valorNodo, NodoDelArbol nodo) {
        boolean respuesta = false;

        while (nodo != null) {
            if (valorNodo == nodo.getValorDelNodo()) {
                respuesta = true;
                break;
            } else if ((valorNodo > nodo.getValorDelNodo() || valorNodo < nodo.getValorDelNodo()) && esHoja(nodo.getValorDelNodo()) == true) {
                break;
            } else if (valorNodo > nodo.getValorDelNodo() && nodo.getNodoDerecho() != null) {
                nodo = nodo.getNodoDerecho();
            } else if (valorNodo < nodo.getValorDelNodo() && nodo.getNodoIzquierdo() != null) {
                nodo = nodo.getNodoIzquierdo();
            }
        }
        return respuesta;
    }

   
    private NodoDelArbol obtenerPadre(int valor) {
        NodoDelArbol auxiliar = this.raizPrincipal;
        NodoDelArbol padre = auxiliar;

        if (this.raizPrincipal.getValorDelNodo() == valor) {
            padre = null;
        } else {
            while (auxiliar != null) {
                if (auxiliar.getNodoDerecho() != null && auxiliar.getNodoDerecho().getValorDelNodo() == valor) {
                    padre = auxiliar;
                    break;
                } else if (auxiliar.getNodoIzquierdo() != null && auxiliar.getNodoIzquierdo().getValorDelNodo() == valor) {
                    padre = auxiliar;
                    break;
                } else {
                    if (valor > auxiliar.getValorDelNodo() && auxiliar.getNodoDerecho() != null) {
                        auxiliar = auxiliar.getNodoDerecho();
                    } else if (valor < auxiliar.getValorDelNodo() && auxiliar.getNodoIzquierdo() != null) {
                        auxiliar = auxiliar.getNodoIzquierdo();
                    }
                }

            }
        }
        return padre;
    }
    

    private boolean esHoja(int dato) {
        NodoDelArbol recorrido = this.raizPrincipal;
        boolean respuesta = false;
        while (recorrido != null) {

            if (recorrido.getValorDelNodo() == dato) {
                if (recorrido.getNodoIzquierdo() == null && recorrido.getNodoDerecho() == null) {
                    respuesta = true;
                    break;
                } else {
                    respuesta = false;
                    break;
                }
            } else if (dato > recorrido.getValorDelNodo() && recorrido.getNodoDerecho()!=null) {
                recorrido = recorrido.getNodoDerecho();
            } else if (dato < recorrido.getValorDelNodo() && recorrido.getNodoIzquierdo()!=null) {
                recorrido = recorrido.getNodoIzquierdo();
            }
        }
        return respuesta;
    }
    
    
    
}
