public interface CustomEnum<T extends Enum<T>> {

    /**
     * 返回序数
     *
     * @return 序数
     */
    int getOrdinal();

    /**
     * 返回枚举的描述
     *
     * @return 枚举的描述
     */
    String getDesc();

}
