> common

    |- annotation 注解定义
    |- base 基类
    |- constants    常量定义
        |- CommonConstants  前缀路径常量、token常量
        |- WebSocketConstant
    |- convert
    |- handler 条件、返回值、分页查询处理器
        
    learning_1：
    @JsonDeserialize(using= DateJsonDeserializer.class)
    @JsonSerialize(using= DateJsonSerializer.class)
    
    public class DateJsonDeserializer extends JsonDeserializer<Date>{
        public static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @Override
        public Date deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
            try{
                if(jsonParser!=null&&StringUtils.isNotEmpty(jsonParser.getText())){
                    return format.parse(jsonParser.getText());
                }else {
                    return null;
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
    
    public class DateJsonSerializer extends JsonSerializer<Date> {
    	public static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	@Override
    	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    		jsonGenerator.writeString(format.format(date));
    	}
    }
 