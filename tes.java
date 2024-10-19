import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.Map;
import java.util.Objects;

public class KeyAwarePageable implements Pageable {

    private final Map<String, AttributeValue> exclusiveStartKey;
    private final int pageSize;

    public KeyAwarePageable(Map<String, AttributeValue> exclusiveStartKey, int pageSize) {
        this.exclusiveStartKey = exclusiveStartKey;
        this.pageSize = pageSize;
    }

    public Map<String, AttributeValue> getExclusiveStartKey() {
        return exclusiveStartKey;
    }

    @Override
    public int getPageNumber() {
        // Como o DynamoDB não utiliza números de página, retornamos 0 ou calculamos com base na presença de ExclusiveStartKey
        return (exclusiveStartKey != null) ? 1 : 0;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        // O offset não é aplicável no DynamoDB, então retornamos 0
        return 0;
    }

    @Override
    public Sort getSort() {
        // O DynamoDB não suporta ordenação personalizada nas consultas, retornamos Sort.unsorted()
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        // Retorna uma nova instância de KeyAwarePageable com o mesmo pageSize
        // O exclusiveStartKey será atualizado após a consulta
        return new KeyAwarePageable(exclusiveStartKey, pageSize);
    }

    @Override
    public Pageable previousOrFirst() {
        // O DynamoDB não permite navegar para a página anterior usando ExclusiveStartKey
        // Retornamos a primeira página
        return first();
    }

    @Override
    public Pageable first() {
        // Retorna uma nova instância sem ExclusiveStartKey para indicar a primeira página
        return new KeyAwarePageable(null, pageSize);
    }

    @Override
    public boolean hasPrevious() {
        // Não há como voltar para a página anterior no DynamoDB usando ExclusiveStartKey
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyAwarePageable that = (KeyAwarePageable) o;
        return pageSize == that.pageSize &&
                Objects.equals(exclusiveStartKey, that.exclusiveStartKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exclusiveStartKey, pageSize);
    }

    @Override
    public String toString() {
        return "KeyAwarePageable{" +
                "exclusiveStartKey=" + exclusiveStartKey +
                ", pageSize=" + pageSize +
                '}';
    }
}
