    package fourman.project1.repository.user;

    import fourman.project1.domain.user.User;

    import jakarta.transaction.Transactional;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Repository;

    @Repository
    @RequiredArgsConstructor
    public class UserRepository {

        private final UserMybatisMapper userMybatisMapper;

        @Transactional
        public void save(User user) {
            System.out.println("rep"+ user.getUsername());
            userMybatisMapper.save(user);
        }


        public boolean existsByUsername(String username) {
            return !userMybatisMapper.existsByUsername(username);
        }
    }
