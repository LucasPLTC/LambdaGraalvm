public Page<Entity> buscarEntidades(
    String codigoEmpresa,
    String codigoConta,
    String statusValue,
    String periodicidadeValue,
    String dataInicialValue,
    String dataFinalValue,
    Pageable pageable
) {
    String ignoreValue = "__ALL__";

    // Se o parâmetro for nulo ou vazio, define para o valor especial
    statusValue = (statusValue != null && !statusValue.isEmpty()) ? statusValue : ignoreValue;
    periodicidadeValue = (periodicidadeValue != null && !periodicidadeValue.isEmpty()) ? periodicidadeValue : ignoreValue;
    dataInicialValue = (dataInicialValue != null && !dataInicialValue.isEmpty()) ? dataInicialValue : ignoreValue;
    dataFinalValue = (dataFinalValue != null && !dataFinalValue.isEmpty()) ? dataFinalValue : ignoreValue;

    return testRepository.findByCodigoEmpresaAndCodigoConta(
        codigoEmpresa,
        codigoConta,
        statusValue,
        periodicidadeValue,
        dataInicialValue,
        dataFinalValue,
        ignoreValue,
        pageable
    );
}
