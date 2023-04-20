import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.List;

class SumList extends DnC<List<Integer>, Integer> {
   
    SumList(List<Integer> list) {
       super(() -> list , x -> x.size() == 1,
        x -> x.get(0), Optional.of(l -> {
        int mid = (l.size() + 1) / 2;

        return Pair.of(() -> l.subList(0,mid), () -> l.subList(mid, l.size()));
        }));
    }
}
