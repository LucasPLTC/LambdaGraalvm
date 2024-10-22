import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.ExpressionAttribute;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface TestRepository {

    String IGNORE_VALUE = "__ALL__"; // Valor especial para ignorar o filtro

    @Query(
        filterExpression = "(:statusValue = :ignoreValue OR #statusName = :statusValue) AND " +
                           "(:periodicidadeValue = :ignoreValue OR #periodicidadeName = :periodicidadeValue) AND " +
                           "(:dataInicialValue = :ignoreValue OR #dataName >= :dataInicialValue) AND " +
                           "(:dataFinalValue = :ignoreValue OR #dataName <= :dataFinalValue)",
        expressionMappingNames = {
            @ExpressionAttribute(key = "#statusName", value = "status"),
            @ExpressionAttribute(key = "#periodicidadeName", value = "periodicidade"),
            @ExpressionAttribute(key = "#dataName", value = "data")
        },
        expressionMappingValues = {
            @ExpressionAttribute(key = ":statusValue", parameterName = "statusValue"),
            @ExpressionAttribute(key = ":periodicidadeValue", parameterName = "periodicidadeValue"),
            @ExpressionAttribute(key = ":dataInicialValue", parameterName = "dataInicialValue"),
            @ExpressionAttribute(key = ":dataFinalValue", parameterName = "dataFinalValue"),
            @ExpressionAttribute(key = ":ignoreValue", parameterName = "ignoreValue")
        }
    )
    Page<Entity> findByCodigoEmpresaAndCodigoConta(
        String codigoEmpresa,
        String codigoConta,
        @Param("statusValue") String statusValue,
        @Param("periodicidadeValue") String periodicidadeValue,
        @Param("dataInicialValue") String dataInicialValue,
        @Param("dataFinalValue") String dataFinalValue,
        @Param("ignoreValue") String ignoreValue,
        Pageable pageable
    );
}
