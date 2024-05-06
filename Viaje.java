class Viaje {
    private Double tiempoEstimado;
    private Direccion origen;
    private List<Parada> destino;
    private boolean finalizoViaje;
    private boolean finalizoParada;
    
	// Constructor
	public Viaje(Double tiempoEstimado, Direccion origen, List<Parada> destino, boolean finalizoViaje, boolean finalizoParada) {
		this.tiempoEstimado = tiempoEstimado;
		this.origen = origen;
		this.destino = destino;
		this.finalizoViaje = finalizoViaje;
		this.finalizoParada = finalizoParada;
	}
	
    public int calcularTiempo() {
        int demoraTotal = 0;
        int numParadas = destino.size();
		
        for (int i = 0; i < numParadas - 1; i++) {
            int distancia = GoogleDistanceMatrixAPI.calcularDistancia(destino.get(i).getDireccion(), destino.get(i + 1).getDireccion());

            demoraTotal += distancia / 100;

            if (destino.get(i).avisaEnParada()) {
                demoraTotal += destino.get(i).getTiempoDeEspera();
            }
        }

        // Supongamos que la velocidad promedio es de 100 metros por minuto
        demoraTotal += GoogleDistanceMatrixAPI.calcularDistancia(origen, destino.get(0).getDireccion()) / 100; // Tiempo desde el origen hasta la primera parada

        return demoraTotal;
    }
}