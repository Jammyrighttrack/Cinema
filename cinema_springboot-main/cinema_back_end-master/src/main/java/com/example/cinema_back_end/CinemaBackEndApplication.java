package com.example.cinema_back_end;

import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.entities.*;
import com.example.cinema_back_end.repositories.*;
import com.example.cinema_back_end.security.service.IUserService;
import com.example.cinema_back_end.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CinemaBackEndApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8081")
                .allowedHeaders("*").allowedMethods("*");
            }
        };
    }

    @Autowired
    private IUserService userService;

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private IBranchRepository branchRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private ISeatRepository seatRepository;

    @Autowired
    private MovieService movieService;

    // Do chưa có trang admin để thêm phim và lịch chiếu nên thêm tạm dữ liệu xuống db để demo
    @PostConstruct
    public void init() {
        // Chạy 1 lần đầu app rồi bỏ comment đoạn này rồi chạy lại để add data ghế ngồi cho phòng 1
    	//Bước 2: chạy tắt comment này rồi chạy lại lần nữa
       Room room = roomRepository.findById(1).orElse(null);

       if(room != null && seatRepository.getSeatByRoom_Id(room.getId()).isEmpty()){
           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("A"+i);
               seat.setActive(true);
               seat.setRoom(room);
               seatRepository.save(seat);
           }

           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("B"+i);
               seat.setActive(true);
               seat.setRoom(room);
               seatRepository.save(seat);
           }
           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("C"+i);
               seat.setActive(true);
               if(i>2 && i<15) {
                   seat.setVip(true);
               }
               seat.setRoom(room);
               seatRepository.save(seat);
           }
           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("D"+i);
               seat.setActive(true);
               if(i>2 && i<15) {
                   seat.setVip(true);
               }
               seat.setRoom(room);
               seatRepository.save(seat);
           }
           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("E"+i);
               seat.setActive(true);
               if(i>2 && i<15) {
                   seat.setVip(true);
               }
               seat.setRoom(room);
               seatRepository.save(seat);
           }
           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("F"+i);
               seat.setActive(true);
               if(i>2 && i<15) {
                   seat.setVip(true);
               }
               seat.setRoom(room);
               seatRepository.save(seat);
           }

           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("G"+i);
               seat.setActive(true);
               if(i>2 && i<15) {
                   seat.setVip(true);
               }
               seat.setRoom(room);
               seatRepository.save(seat);
           }
           for(int i=1;i<=16;i++){
               Seat seat = new Seat();
               seat.setName("H"+i);
               seat.setActive(true);
               if(i>2 && i<15) {
                   seat.setVip(true);
               }
               seat.setRoom(room);
               seatRepository.save(seat);
           }
       }


        List<User> users = userService.findAll();

        if (users.isEmpty()) {
            User admin = new User();
            Set<Role> roles = new HashSet<>();
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            Role roleClient = new Role();
            roleClient.setName("ROLE_CLIENT");
            roles.add(roleAdmin);
            roles.add(roleClient);
            admin.setFullName("Admin");
            admin.setUsername("admin@gmail.com");
            admin.setPassword("123456");
            admin.setRoles(roles);
            userService.save(admin);
         }

        //helper


        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            MovieDTO nhocTrum = new MovieDTO();
            nhocTrum.setName("Nhóc Trùm: Nối Nghiệp Gia Đình");
            nhocTrum.setSmallImageURl("https://www.cgv.vn/media/catalog/product/cache/1/small_image/240x388/dd828b13b1cb77667d034d5f59a82eb6/p/o/poster_boss_baby_2_24.12.2021_1_1_1__1.jpg");
            nhocTrum.setShortDescription("Nhóc trùm Ted giờ đây đã trở thành một triệu phú nổi tiếng trong khi Tim lại có một cuộc sống đơn giản bên vợ anh Carol và hai cô con gái nhỏ yêu dấu. Mỗi mùa Giáng sinh tới, cả Tina và Tabitha đều mong được gặp chú Ted");
            nhocTrum.setLongDescription("Nhóc trùm Ted giờ đây đã trở thành một triệu phú nổi tiếng trong khi Tim lại có một cuộc sống đơn giản bên vợ anh Carol và hai cô con gái nhỏ yêu dấu. Mỗi mùa Giáng sinh tới, cả Tina và Tabitha đều mong được gặp chú Ted nhưng dường như hai anh em nhà Templeton nay đã không còn gần gũi như xưa. Nhưng bất ngờ thay khi Ted lại có màn tái xuất không thể hoành tráng hơn khi đáp thẳng máy bay trực thăng tới nhà Tim trước sự ngỡ ngàng của cả gia đình.");
            nhocTrum.setLargeImageURL("https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/r/s/rsz_dr-strange-980x448.jpg");
            nhocTrum.setDirector("Tom McGrath");
            nhocTrum.setActors("Amy Sedaris, Jeff Goldblum, James Marsden");
            nhocTrum.setCategories("Hoạt Hình");
            nhocTrum.setReleaseDate(LocalDate.parse("24/12/2021", formatter));
            nhocTrum.setDuration(105);
            nhocTrum.setTrailerURL("https://www.youtube.com/embed/Lv8nL2q8yRI");
            nhocTrum.setLanguage("Tiếng Anh với phụ đề tiếng Việt và lồng tiếng Việt");
            nhocTrum.setRated("P - PHIM DÀNH CHO MỌI ĐỐI TƯỢNG");
            nhocTrum.setIsShowing(1);

            MovieDTO savedMovie1 = movieService.addNewMovie(nhocTrum);
            Movie movie1 = movieRepository.findById(savedMovie1.getId()).orElseThrow(() -> new RuntimeException("Movie not found"));


            MovieDTO venom = new MovieDTO();

            venom.setName("Venom: Đối Mặt Tử Thù");
            venom.setSmallImageURl(
                    "https://www.cgv.vn/media/catalog/product/cache/1/small_image/240x388/dd828b13b1cb77667d034d5f59a82eb6/p/o/poster_venom_121121_1__1.jpg"
            );
            venom.setShortDescription(
                    "Siêu bom tấn #VENOM: LET THERE BE CARNAGE hứa hẹn trận chiến khốc liệt nhất giữa Venom và kẻ thù truyền kiếp, Carnage."
            );
            venom.setLongDescription(
                    "Siêu bom tấn #VENOM: LET THERE BE CARNAGE hứa hẹn trận chiến khốc liệt nhất giữa Venom và kẻ thù truyền kiếp, Carnage."
            );
            venom.setLargeImageURL(
                    "https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/b/l/blackpink-rolling_1_.jpg"
            );
            venom.setDirector("Andy Serkis");
            venom.setActors("Tom Hardy, Michelle Williams, Woody Harrelson, Naomie Harris");
            venom.setCategories("Hành Động, Khoa Học Viễn Tưởng, Phiêu Lưu, Thần thoại");
            venom.setReleaseDate(LocalDate.parse("10/12/2021", formatter));
            venom.setDuration(97);
            venom.setTrailerURL("https://www.youtube.com/embed/EVWdzVtSh1I");
            venom.setLanguage("Tiếng Anh - Phụ đề Tiếng Việt");
            venom.setRated("C13 - PHIM CẤM KHÁN GIẢ DƯỚI 13 TUỔI");
            venom.setIsShowing(1);

            MovieDTO savedMovie2 = movieService.addNewMovie(venom);
            Movie movie2 = movieRepository.findById(savedMovie2.getId()).orElseThrow(() -> new RuntimeException("Movie not found"));


            //matran
            MovieDTO maTran = new MovieDTO();

            maTran.setName("Ma Trận: Hồi Sinh");
            maTran.setSmallImageURl(
                    "https://www.cgv.vn/media/catalog/product/cache/1/small_image/240x388/dd828b13b1cb77667d034d5f59a82eb6/p/o/poster_matrix_4_1__1.jpg"
            );
            maTran.setShortDescription(
                    "Sau 20 năm, siêu phẩm ma trận đã trờ lại với người xem, Neo is back! Liệu đây có phải phần kết cho franchise này"
            );
            maTran.setLongDescription(
                    "Ma Trận: Hồi Sinh là phần phim tiếp theo rất được trông đợi của loạt phim “Ma Trận” đình đám, đã góp phần tái định nghĩa thể loại phim khoa học viễn tưởng. Phần phim mới nhất này đón chào sự trở lại của cặp đôi Keanu Reeves và Carrie-Anne Moss với vai diễn biểu tượng đã làm nên tên tuổi của họ, Neo và Trinity. Ngoài ra, phim còn có sự góp mặt của dàn diễn viên đầy tài năng gồm Yahya Abdul-Mateen II, Jessica Henwick, Jonathan Groff, Neil Patrick Harris, Priyanka Chopra Jonas và Christina Ricci."
            );
            maTran.setLargeImageURL(
                    "https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/b/l/blackpink-rolling_1_.jpg"
            );
            maTran.setDirector("Lana Wachowski");
            maTran.setActors(
                    "Keanu Reeves, Carrie-Anne Moss, Yahya Abdul-Mateen II, Jessica Henwick, Jonathan Groff, Neil Patrick Harris, Priyanka Chopra Jonas và Christina Ricci"
            );
            maTran.setCategories("Hành Động, Khoa Học Viễn Tưởng");
            maTran.setReleaseDate(LocalDate.parse("24/12/2021", formatter));
            maTran.setDuration(148);
            maTran.setTrailerURL("https://www.youtube.com/embed/l2UTOJC5Tbk");
            maTran.setLanguage("Tiếng Anh - Phụ đề Tiếng Việt, Phụ đề Tiếng Hàn");
            maTran.setRated("C18 - PHIM CẤM KHÁN GIẢ DƯỚI 18 TUỔI");
            maTran.setIsShowing(1);

            MovieDTO savedMovie3 = movieService.addNewMovie(maTran);
            Movie movie3 = movieRepository.findById(savedMovie3.getId()).orElseThrow(() -> new RuntimeException("Movie not found"));


            MovieDTO doremon = new MovieDTO();

            doremon.setName("Doraemon: Ôi Bạn Ơi 2");
            doremon.setSmallImageURl(
                    "https://www.cgv.vn/media/catalog/product/cache/1/small_image/240x388/dd828b13b1cb77667d034d5f59a82eb6/p/o/poster_doremon_2_1__1.jpg"
            );
            doremon.setShortDescription(
                    "Một ngày nọ, Nobita vô tình tìm thấy chú gấu bông cũ, món đồ chơi chất chứa bao kỉ niệm cùng người bà đáng kính. Với khát khao “muốn gặp bà lần nữa”, Nobita đã trở về quá khứ bằng cổ máy thời gian, bất chấp sự phản đối của Doraemon"
            );
            doremon.setLongDescription(
                    "Một ngày nọ, Nobita vô tình tìm thấy chú gấu bông cũ, món đồ chơi chất chứa bao kỉ niệm cùng người bà đáng kính. Với khát khao “muốn gặp bà lần nữa”, Nobita đã trở về quá khứ bằng cổ máy thời gian, bất chấp sự phản đối của Doraemon. Dù ngạc nhiên, bà vẫn tin cậu thiếu niên lớn tướng trước mặt mình là cháu mình. Trước nguyện vọng tha thiết “mong được thấy cháu dâu một lần”, chuyến phiêu lưu của Doraemon và Nobita bắt đầu. Nobita muốn cho bà xem đám cưới của mình, nhưng đúng ngày thành hôn với Shizuka, chú rể Nobita lại trốn mất? Jaian và Suneo chạy đôn chạy đáo tìm bạn, còn Shizuka vẫn tin tưởng chờ đợi Nobita. Để thực hiện nguyện vọng của bà, đáp lại niềm tin của gia đình, bạn bà và Shizuka yêu quý, Nobita sẽ cùng Doraemon du hành vượt thời gian. Họ sẽ mang đến cho chúng ta một câu chuyện cảm động đến rơi lệ về quan hệ con người, kết nối giữa quá khứ, hiện tại và tương lai."
            );
            doremon.setLargeImageURL(
                    "https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/d/o/doreamon.jpg"
            );
            doremon.setDirector("Ryuichi Yagi, Takashi Yamazaki");
            doremon.setActors(
                    "Wasabi Mizuta, Megumi Oohara, Yumi Kakazu, Subaru Kimura, Tomokazu Seki"
            );
            doremon.setCategories("Hài, Hoạt Hình");
            doremon.setReleaseDate(LocalDate.parse("17/12/2021", formatter));
            doremon.setDuration(96);
            doremon.setTrailerURL("https://www.youtube.com/embed/GXnOs4Hj8MA");
            doremon.setLanguage("Tiếng Nhật - Phụ đề Tiếng Việt; Lồng tiếng");
            doremon.setRated("P - PHIM DÀNH CHO MỌI ĐỐI TƯỢNG");
            doremon.setIsShowing(1);

            MovieDTO savedMovie4 = movieService.addNewMovie(doremon);
            Movie movie4 = movieRepository.findById(savedMovie4.getId()).orElseThrow(() -> new RuntimeException("Movie not found"));

            //nguoi nhen
            MovieDTO nguoiNhen = new MovieDTO();

            nguoiNhen.setName("Người Nhện: Không Còn Nhà");
            nguoiNhen.setSmallImageURl(
                    "https://www.cgv.vn/media/catalog/product/cache/1/small_image/240x388/dd828b13b1cb77667d034d5f59a82eb6/s/n/snwh_poster_bluemontage_4x5fb_1__1.jpg"
            );
            nguoiNhen.setShortDescription(
                    "Đa vũ trụ được mở ra, những kẻ phản diện nào sẽ trạm chán Spidey, cùng đón xem nhá"
            );
            nguoiNhen.setLongDescription(
                    "Lần đầu tiên trong lịch sử điện ảnh của Người Nhện, thân phận người hàng xóm thân thiện bị lật mở, khiến trách nhiệm làm một Siêu Anh Hùng xung đột với cuộc sống bình thường và đặt người anh quan tâm nhất vào tình thế nguy hiểm. Khi anh nhờ đến giúp đỡ của Doctor Strange để khôi phục lại bí mật, phép thuật đã gây ra lỗ hổng thời không, giải phóng những ác nhân mạnh mẽ nhất từng đối đầu với Người Nhện từ mọi vũ trụ. Bây giờ, Peter sẽ phải vượt qua thử thách lớn nhất của mình, nó sẽ thay đổi không chỉ tương lai của chính anh mà còn là tương lai của cả Đa Vũ Trụ."
            );
            nguoiNhen.setLargeImageURL(
                    "https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/r/s/rsz_dr-strange-980x448.jpg"
            );
            nguoiNhen.setDirector("Jon Watts");
            nguoiNhen.setActors(
                    "Tom Holland, Zendaya, Benedict Cumberbatch, Jacob Batalon, Jon Favreau"
            );
            nguoiNhen.setCategories("Hành Động, Phiêu Lưu");
            nguoiNhen.setReleaseDate(LocalDate.parse("17/12/2021", formatter));
            nguoiNhen.setDuration(149);
            nguoiNhen.setTrailerURL("https://www.youtube.com/embed/daHCu_jU5mQ");
            nguoiNhen.setLanguage("Tiếng Anh - Phụ đề Tiếng Việt");
            nguoiNhen.setRated("C13 - PHIM CẤM KHÁN GIẢ DƯỚI 13 TUỔI");
            nguoiNhen.setIsShowing(1);

            MovieDTO savedMovie5 = movieService.addNewMovie(nguoiNhen);
            Movie movie5 = movieRepository.findById(savedMovie5.getId()).orElseThrow(() -> new RuntimeException("Movie not found"));

            //blackpink
            MovieDTO blackPink = new MovieDTO();

            blackPink.setName("BlackPink The Movie");
            blackPink.setSmallImageURl(
                    "https://www.cgv.vn/media/catalog/product/cache/1/small_image/240x388/dd828b13b1cb77667d034d5f59a82eb6/p/o/poster_blackpink_vie_2_1__1.jpg"
            );
            blackPink.setShortDescription(
                    "Nhóm nhạc nữ được yêu thích toàn cầu, BLACKPINK sẽ kỷ niệm năm thứ 5 hoạt động của nhóm với việc phát hành BLACKPINK THE MOVIE"
            );
            blackPink.setLongDescription(
                    "Nhóm nhạc nữ được yêu thích toàn cầu, BLACKPINK sẽ kỷ niệm năm thứ 5 hoạt động của nhóm với việc phát hành BLACKPINK THE MOVIE, đây cũng như là món quà đặc biệt dành tặng cho các BLINK— fandom của BLACKPINK — bộ phim sẽ tái hiện một cách sống động những kỷ niệm không thể quên cùng những màn trình diễn đầy cuồng nhiệt đúng tinh thần lễ hội."
            );
            blackPink.setLargeImageURL(
                    "https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/b/l/blackpink-rolling_1_.jpg"
            );
            blackPink.setDirector("Su Yee Jung, Oh Yoon-Dong");
            blackPink.setActors("JISOO, JENNIE, ROSÉ, LISA");
            blackPink.setCategories("Phim tài liệu");
            blackPink.setReleaseDate(LocalDate.parse("24/12/2021", formatter));
            blackPink.setDuration(99);
            blackPink.setTrailerURL("https://www.youtube.com/embed/Q_rK9UlUN-Q");
            blackPink.setLanguage("Tiếng Hàn - Phụ đề tiếng Việt");
            blackPink.setRated("P - PHIM DÀNH CHO MỌI ĐỐI TƯỢNG");
            blackPink.setIsShowing(1);

            MovieDTO savedMovie6 = movieService.addNewMovie(blackPink);
            Movie movie6 = movieRepository.findById(savedMovie6.getId()).orElseThrow(() -> new RuntimeException("Movie not found"));


            List<Branch> listBranch = branchRepository.findAll();
            if(listBranch.isEmpty()){
                Branch branch1 = new Branch();
                branch1.setName("HUYCINEMA Hà Đông");
                branch1.setDiaChi("Tầng 4, Mê Linh Plaza Hà Đông, Đ. Tô Hiệu, P, Hà Đông, Hà Nội");
                branch1.setPhoneNo("0938473829");
                branch1.setImgURL("https://www.google.com/maps/vt/data=01jbed2RV46dgYPWYrkUjQ6y9E_UiFnVBsCgIdJWh4TGqJw3K1Qg_A4phqg094CBuRXesGa3QOof0UPRtY3zUWjOKScSn-0JuYoAlbcSKeYWV9wooMdNPaY7iL3Yd9PJjxicmzPKGds-zZRAZ9hqPRix1Trxq2vTQ7GZDWXjNJrqjn2tqL8zO0gSrZgSmarAH0jaD9Ux5tVTaZCwchq2_nNCrs2vjOU7FeXQsPRqfM3jgoYPRe43jeLkd4KGtweeXwUPgV2AeBFj9yTmjBgHSswDBrmGoJkjk-0uRIIO6LCZyMAsSW1p7-gLsUI5nJF6zWCNKmesZ3Jd3I-17zEAOz3AmLMuLkRgiFkICakIuG9B0DkjzVK2P4jN203i4DNkXTpoxKHTMv9dZG-ZoW1A9w7Q5rzSukE8Zdt3GMMei-w-THF-qL3znIiK3nQKq1_BRtnFTvhXduCYpHCo3ZvIiBz4WNKjovXd9ppG-OlRtFLYh8kYvlCKWkO0bmkBeQXoT4mqHQXm80zs_P2CB4xE3bbtoUPgaNo2-5eUcO1CPh6n3DKUdkgOIjRflGoWijmrhJO_45gguPAqZ7ZcXmY_isBf4PnWWJnzO2VAHVQwqwYIJ503CVbm3bmWoX3nVyqThCPj3fFsvjxCH-uYT0VXi3IFc02ktKuirKrsSo2rcgTcTqto0LlmkPAm34wOAr2KmMCfiqJrjKKMCn62v7WefBbU3VLI7Z8pLIgrG4l258FsyN7unVKWcVl3TVnBWp-acw9Y9AmM-nu8OzD7HSpMjJM3X28MJGj9LIIC1WsEdVL0Jhq8x9vBkY9F0RT_XTLQxbIJYa3v0B9x6KlkdOlWqTQTHvc5HQz8OV0JQYp5roWX5WcIx06_gkNOvisnf-J3aeMgzGVGs_-dZUXPwNseutiOPQXyy5NfzhZuJDOAmCJLXEAhmB6BFzFMbATI5zQ9v-D2lDvsjYq2U3Mt7Ctp6HlZVb4DGhzu7FYZkBQ11KkbQthBNKrZQ3kTiVQNNf13Osr9fIK4W6m6R3FtkxqnPshtlc-PYArGqZimsKnxgxxwt1spelowhnI55qFR9wa6UdJgeyGGRfVyfDpFiRKUkwuB7Vip?h=505&w=561&scale=1");
                branch1 = branchRepository.save(branch1);
                Room room1_1 = new Room();
                room1_1.setName("Phòng 101");
                room1_1.setBranch(branch1);
                room1_1.setCapacity(40);
                room1_1.setTotalArea(80);
                room1_1.setImgURL("http://hdradio.vn/upload/hinhanh/Cinema-tong-hop/cinema-thiet-ke/Cinema-kd100/cinema-hdradio.jpg");
                Room r1 = roomRepository.save(room1_1);
                Schedule schedule1_1 = new Schedule();
                schedule1_1.setBranch(branch1);
                schedule1_1.setMovie(movie5);
                schedule1_1.setRoom(r1);
                schedule1_1.setStartDate(LocalDate.parse("2021-01-05"));
                schedule1_1.setStartTime(LocalTime.parse("10:15"));
                schedule1_1.setPrice(70000);
                scheduleRepository.save(schedule1_1);

                Schedule schedule5 = new Schedule();
                schedule5.setBranch(branch1);
                schedule5.setMovie(movie5);
                schedule5.setRoom(r1);
                schedule5.setStartDate(LocalDate.parse("2021-01-05"));
                schedule5.setStartTime(LocalTime.parse("13:05"));
                schedule5.setPrice(70000);
                scheduleRepository.save(schedule5);

                Schedule schedule6_1 = new Schedule();
                schedule6_1.setBranch(branch1);
                schedule6_1.setMovie(movie5);
                schedule6_1.setRoom(r1);
                schedule6_1.setStartDate(LocalDate.parse("2021-01-05"));
                schedule6_1.setStartTime(LocalTime.parse("14:05"));
                schedule6_1.setPrice(70000);
                scheduleRepository.save(schedule6_1);

                Schedule schedule7 = new Schedule();
                schedule7.setBranch(branch1);
                schedule7.setMovie(movie5);
                schedule7.setRoom(r1);
                schedule7.setStartDate(LocalDate.parse("2021-01-05"));
                schedule7.setStartTime(LocalTime.parse("16:20"));
                schedule7.setPrice(70000);
                scheduleRepository.save(schedule7);


                Schedule schedule8 = new Schedule();
                schedule8.setBranch(branch1);
                schedule8.setMovie(movie5);
                schedule8.setRoom(r1);
                schedule8.setStartDate(LocalDate.parse("2021-01-05"));
                schedule8.setStartTime(LocalTime.parse("16:20"));
                schedule8.setPrice(70000);
                scheduleRepository.save(schedule8);

                Schedule schedule9 = new Schedule();
                schedule9.setBranch(branch1);
                schedule9.setMovie(movie5);
                schedule9.setRoom(r1);
                schedule9.setStartDate(LocalDate.parse("2021-01-06"));
                schedule9.setStartTime(LocalTime.parse("16:20"));
                schedule9.setPrice(70000);
                scheduleRepository.save(schedule9);

                Schedule schedule10 = new Schedule();
                schedule10.setBranch(branch1);
                schedule10.setMovie(movie5);
                schedule10.setRoom(r1);
                schedule10.setStartDate(LocalDate.parse("2021-01-06"));
                schedule10.setStartTime(LocalTime.parse("19:20"));
                schedule10.setPrice(70000);
                scheduleRepository.save(schedule10);

                Schedule schedule = new Schedule();
                schedule.setBranch(branch1);
                schedule.setMovie(movie6);
                schedule.setRoom(r1);
                schedule.setStartDate(LocalDate.parse("2021-01-05"));
                schedule.setStartTime(LocalTime.parse("19:15"));
                schedule.setPrice(70000);
                scheduleRepository.save(schedule);

                Room room2_1 = new Room();
                room2_1.setName("Phòng 202");
                room2_1.setBranch(branch1);
                room2_1.setCapacity(40);
                room2_1.setTotalArea(80);
                room2_1.setImgURL("http://hdradio.vn/upload/hinhanh/Cinema-tong-hop/cinema-thiet-ke/Cinema-kd100/cinema-hdradio.jpg");
                Room r2 = roomRepository.save(room2_1);
                Schedule schedule2_1 = new Schedule();
                schedule2_1.setBranch(branch1);
                schedule2_1.setMovie(movie6);
                schedule2_1.setRoom(r2);
                schedule2_1.setStartDate(LocalDate.parse("2021-01-05"));
                schedule2_1.setStartTime(LocalTime.parse("10:15"));
                schedule2_1.setPrice(70000);
                scheduleRepository.save(schedule2_1);

                Schedule schedule77 = new Schedule();
                schedule77.setBranch(branch1);
                schedule77.setMovie(movie5);
                schedule77.setRoom(r2);
                schedule77.setStartDate(LocalDate.parse("2021-01-05"));
                schedule77.setStartTime(LocalTime.parse("16:20"));
                schedule77.setPrice(70000);
                scheduleRepository.save(schedule77);


                Room room3_1 = new Room();
                room3_1.setName("Phòng 303");
                room3_1.setBranch(branch1);
                room3_1.setCapacity(40);
                room3_1.setTotalArea(80);
                room3_1.setImgURL("http://hdradio.vn/upload/hinhanh/Cinema-tong-hop/cinema-thiet-ke/Cinema-kd100/cinema-hdradio.jpg");
                Room r3 = roomRepository.save(room3_1);
                Schedule schedule3_1 = new Schedule();
                schedule3_1.setBranch(branch1);
                schedule3_1.setMovie(movie2);
                schedule3_1.setRoom(r3);
                schedule3_1.setStartDate(LocalDate.parse("2021-01-05"));
                schedule3_1.setStartTime(LocalTime.parse("10:15"));
                schedule3_1.setPrice(70000);
                scheduleRepository.save(schedule3_1);

                Schedule schedule88 = new Schedule();
                schedule88.setBranch(branch1);
                schedule88.setMovie(movie5);
                schedule88.setRoom(r3);
                schedule88.setStartDate(LocalDate.parse("2021-01-05"));
                schedule88.setStartTime(LocalTime.parse("16:20"));
                schedule88.setPrice(70000);
                scheduleRepository.save(schedule88);

                Room room4_1 = new Room();
                room4_1.setName("Phòng 404");
                room4_1.setBranch(branch1);
                room4_1.setCapacity(40);
                room4_1.setTotalArea(80);
                room4_1.setImgURL("http://hdradio.vn/upload/hinhanh/Cinema-tong-hop/cinema-thiet-ke/Cinema-kd100/cinema-hdradio.jpg");
                Room r4 = roomRepository.save(room4_1);

                Schedule schedule99 = new Schedule();
                schedule99.setBranch(branch1);
                schedule99.setMovie(movie5);
                schedule99.setRoom(r4);
                schedule99.setStartDate(LocalDate.parse("2021-01-05"));
                schedule99.setStartTime(LocalTime.parse("16:20"));
                schedule99.setPrice(70000);
                scheduleRepository.save(schedule99);

                Branch branch2 = new Branch();
                branch2.setName("HUYCINEMA Thủ Đức");
                branch2.setDiaChi("216 Đ. Võ Văn Ngân, Bình Thọ, Thủ Đức, Thành phố Hồ Chí Minh");
                branch2.setPhoneNo("1900 6017");
                branch2.setImgURL("https://www.google.com/maps/vt/data=TZeUNl_xwzxmDpHYWKkbDv_7amlZzoi4kaRvCEATRTtis3KKxsH0tcFvyiR7Gjt4G3NufaHQaPOcn3pMPNABNgbW2ZoipmmEo6PKNCFhm8CuQbuASrxxaRyviyUG78mD1AVOf1D2fJxIjyEmphS20Wo9dgRW8TJBXekAhiaiGu8g");
                branch2 = branchRepository.save(branch2);
                room1_1.setBranch(branch2);
                room2_1.setBranch(branch2);
                room3_1.setBranch(branch2);
                Room r5 = roomRepository.save(room1_1);
                Room r6 = roomRepository.save(room2_1);
                Room r7 = roomRepository.save(room3_1);
                Schedule schedule11 = new Schedule();
                schedule11.setBranch(branch2);
                schedule11.setMovie(movie5);
                schedule11.setRoom(r5);
                schedule11.setStartDate(LocalDate.parse("2021-01-05"));
                schedule11.setStartTime(LocalTime.parse("10:15"));
                schedule11.setPrice(70000);
                scheduleRepository.save(schedule11);


                Branch branch3 = new Branch();
                branch3.setName("HUYCINEMA Ba Đình");
                branch3.setDiaChi("29 P. Liễu Giai, Ngọc Khánh, Ba Đình, Hà Nội 100000");
                branch3.setPhoneNo("1900 6017");
                branch3.setImgURL("https://www.google.com/maps/vt/data=yhfVddn9tdyWNfmuCzyFU_TR8pm30CLi5oeQTFnFB7qV90WT3OL_ETKkEjQjn3j6zlMuz-VSN_AgZRDCghvF5y2JCVivnwi-sOuKKWT4bSEOf0FZ2-nwoNYSRZH--yH_VpazHsQ9huADdpfR_j3ZnNMEfU_hwJXzSef8AcxHTcqA");
                branch3 = branchRepository.save(branch3);
                room1_1.setBranch(branch3);
                room2_1.setBranch(branch3);
                room3_1.setBranch(branch3);
                room4_1.setBranch(branch3);
                Room r8= roomRepository.save(room1_1);
                Room r9 = roomRepository.save(room2_1);
                Room r10 = roomRepository.save(room3_1);
                Room r11 = roomRepository.save(room4_1);
                Schedule schedule12 = new Schedule();
                schedule12.setBranch(branch3);
                schedule12.setMovie(movie5);
                schedule12.setRoom(r8);
                schedule12.setStartDate(LocalDate.parse("2021-01-05"));
                schedule12.setStartTime(LocalTime.parse("10:15"));
                schedule12.setPrice(70000);
                scheduleRepository.save(schedule12);
                Schedule schedule13 = new Schedule();
                schedule13.setBranch(branch3);
                schedule13.setMovie(movie6);
                schedule13.setRoom(r9);
                schedule13.setStartDate(LocalDate.parse("2021-01-05"));
                schedule13.setStartTime(LocalTime.parse("22:15"));
                schedule13.setPrice(70000);
                scheduleRepository.save(schedule13);

                Branch branch4 = new Branch();
                branch4.setName("HUYCINEMA Phạm Hùng");
                branch4.setDiaChi("Phạm Hùng, Mỹ Đình, Nam Từ Liêm, Hà Nội");
                branch4.setPhoneNo("1900 6017");
                branch4.setImgURL("https://www.google.com/maps/vt/data=YVeSKtAT_T4Tie7xjlIh8lVV_CPmpsr36ayQG-gTEGBZtEKRSSUuLnFbj1HBbGxrYJUS3T3ib8llvuVuiSE85HJYK54JW899mhPMP0BWDwBch-utK9g-_kUPd2rsaEpMLmwUd3R9SO67_S6eUEcY0SfqeXAfB2p9NizW8eGSgD63");
                branch4 = branchRepository.save(branch4);
                room1_1.setBranch(branch4);
                room2_1.setBranch(branch4);
                room3_1.setBranch(branch4);
                room4_1.setBranch(branch4);
                Room r12 = roomRepository.save(room1_1);
                roomRepository.save(room2_1);
                roomRepository.save(room3_1);
                roomRepository.save(room4_1);

                Schedule schedule14 = new Schedule();
                schedule14.setBranch(branch4);
                schedule14.setMovie(movie5);
                schedule14.setRoom(r12);
                schedule14.setStartDate(LocalDate.parse("2021-01-05"));
                schedule14.setStartTime(LocalTime.parse("10:15"));
                schedule14.setPrice(70000);
                scheduleRepository.save(schedule14);
            }
        }
   }


    public static void main(String[] args) {
        SpringApplication.run(CinemaBackEndApplication.class, args);
    }

}
