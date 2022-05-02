package pl.mvasio.snake.domain;

class AppleTest {

//    static Stream<Arguments> correctPointAmounts(){
//        return Stream.of(
//                Arguments.of(0),
//                Arguments.of(12),
//                Arguments.of(Integer.MAX_VALUE)
//        );
//    }
//    static Stream<Arguments> incorrectPointAmounts(){
//        return Stream.of(
//                Arguments.of(Integer.MIN_VALUE),
//                Arguments.of(-12),
//                Arguments.of(-1)
//        );
//    }
//
//    Apple apple;
//
//    @BeforeEach
//    void initializeApple(){
//        this.apple = new Apple();
//    }
//
//    @Test
//    void setImageView_ShouldChangeImageView_ForNotNullImageView(){
//        ImageView iView = new ImageView();
//
//        apple.setImageView(iView);
//
//        assertEquals(apple.getImageView(), iView);
//    }
//
//    @Test
//    void setImageView_ShouldSetImageViewFitToBoardGrid_ForNotNullImageView() {
//        assertAll(
//                () -> assertEquals(BoardProperties.FIELD_WIDTH, apple.getImageView().getFitWidth()),
//                () -> assertEquals(BoardProperties.FIELD_HEIGHT, apple.getImageView().getFitHeight())
//        );
//    }
//
//    @ParameterizedTest
//    @NullSource
//    void setImageView_ShouldNotChangeImageView_ForNullImageView(ImageView iView){
//        assertThrows(AssertionError.class, () -> apple.setImageView(iView));
//    }
//
//    @ParameterizedTest
//    @MethodSource("correctPointAmounts")
//    void constructor_ShouldSetPointAmount_ForCorrectPointAmount(int p) {
//        Apple apple = new Apple(new ImageView(), p);
//        int points = apple.getPointAmount();
//
//        assertEquals(p, points);
//    }
//
//    @ParameterizedTest
//    @MethodSource("incorrectPointAmounts")
//    void constructor_ShouldThrowAssertionError_ForIncorrectPointAmount(int p) {
//        assertThrows(AssertionError.class, () -> new Apple(new ImageView(), p));
//    }
}