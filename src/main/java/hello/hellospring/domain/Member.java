package hello.hellospring.domain;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    private String name;
    private Long id;
}
