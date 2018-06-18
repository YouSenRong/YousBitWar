/* Generated By:JavaCC: Do not edit this line. YousBitWarParser.java */
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

public class YousBitWarParser implements YousBitWarParserConstants {
  //敌方上一次的策略,0表示背叛,1表示合作
  private int Last_enermy_strategy = 0;
  //当前进行的回合数
  private int Current = 1;
  //此次采取的策�?,0表示背叛,1表示合作
  private int Result = 0;
  //未匹配的花括�?"{"的数�?
  private int brace_num_global = 0;
  //标记是否已经发生return�?
  private int return_flag_global = 0;
  //产生随机�?
  Random random = new Random();
  private ExternData externData = new ExternData();
  public static void main(String args[]) throws  ParseException
  {
    File file = new File("strategy_4.txt");
    try
    {
      FileInputStream fileInputStream = new FileInputStream(file);
          YousBitWarParser yousBitWarParser = new YousBitWarParser(fileInputStream);
          switch (yousBitWarParser.program())
          {
            case 0 :
                System.out.println("OK.");
                break;
            case 1 :
                System.out.println("Goodbye.");
                break;
                default :
                break;
          }
        }
        catch (Exception e)
        {
          System.out.println("NOK.");
          System.out.println(e.getMessage());
        }
        catch (Error e)
        {
          System.out.println("Oops.");
          System.out.println(e.getMessage());
        }
  }
  public int sent_message(int current, int last_enermy_strategy)
  {
    Current = current;
        Last_enermy_strategy = last_enermy_strategy;
        //在Enermy_ArrayList中添加敌方上�?次采用的策略
        externData.Enermy_ArrayList.add(Current-1, Last_enermy_strategy);
        //初始化未匹配的花括号" { "数量�?0
        brace_num_global = 0;
        //初始化是否已经发生返回的标记变量，return_flag_global
        return_flag_global = 0;
        return 1;
  }
  public int get_result()
  {
    return this.Result;
  }

//program -> function_declaration
  final public int program() throws ParseException {
    function_statement();
    {if (true) return 1;}
    throw new Error("Missing return statement in function");
  }

//function_declaration -> <STRATEGY> <ID> statement_block
  final public void function_statement() throws ParseException {
    jj_consume_token(STRATEGY);
    jj_consume_token(ID);
    statement_block();
  }

//statement_block -> "{ " statement_list "}"
  final public void statement_block() throws ParseException {
  Token temp_token;
    jj_consume_token(LBRACE);
    brace_num_global = brace_num_global + 1;
    statement_list();
    jj_consume_token(RBRACE);
    brace_num_global = brace_num_global - 1;
        if( (return_flag_global == 1) && (brace_num_global == 1) )
        {
          token = getNextToken();
          while( !token.image.equals("END") )
          {
            token = getNextToken();
          }
        }
  }

//statement_list -> (statement)*
  final public void statement_list() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
      case IF:
      case WHILE:
      case RETURN:
      case LBRACE:
      case ID:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      statement();
    }
  }

//statement -> variable_declaration | assign_statement | if_statement | while_statement | return_statement | statement_block
  final public void statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      variable_declaration();
      break;
    case ID:
      assign_statement();
      break;
    case IF:
      if_statement();
      break;
    case WHILE:
      while_statement();
      break;
    case RETURN:
      return_statement();
    return_flag_global = 1;
      break;
    case LBRACE:
      statement_block();
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//variable_declaration -> INT ID (, ID)* ;
  final public void variable_declaration() throws ParseException {
  Token temp_token;
    jj_consume_token(INT);
    temp_token = jj_consume_token(ID);
    if(externData.ID_HashMap.get(temp_token.image) == null)
    {
      externData.ID_HashMap.put(temp_token.image, 0);
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      temp_token = jj_consume_token(ID);
    if(externData.ID_HashMap.get(temp_token.image) == null)
    {
      externData.ID_HashMap.put(temp_token.image, 0);
    }
    }
    jj_consume_token(SEMICOLON);
  }

//assign_statement -> ID "=" expression ";"
  final public void assign_statement() throws ParseException {
  int value = 0;
  Token temp_token;
    temp_token = jj_consume_token(ID);
    jj_consume_token(ASSIGN);
    value = expression();
    jj_consume_token(SEMICOLON);
//    System.out.println("assign vlaue:" + value);
    if(externData.ID_HashMap.get(temp_token.image) != null)
    {
      externData.ID_HashMap.put(temp_token.image, value);
    }
  }

//if_statement -> "if" "(" expression ")" [statement_block]
  final public void if_statement() throws ParseException {
  int temp_value = 0;
  int temp_brace_num = 0;
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    temp_value = expression();
    jj_consume_token(RPAREN);
    if(temp_value != 1)
    {
     temp_brace_num = 0;
     token = getNextToken();
     if(token.image.equals("{"))
     {
       temp_brace_num = temp_brace_num + 1;
       brace_num_global = brace_num_global + 1;
     }
     while(temp_brace_num > 0)
     {
        token = getNextToken();
        if(token.image.equals("{"))
        {
          temp_brace_num = temp_brace_num + 1;
          brace_num_global = brace_num_global + 1;
        }
        else if(token.image.equals("}"))
        {
          temp_brace_num = temp_brace_num -1;
          brace_num_global = brace_num_global - 1;
        }
     }
   }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACE:
      statement_block();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ELSEIF:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      jj_consume_token(ELSEIF);
    if(temp_value == 1)
    {
       temp_brace_num = 0;
       token = getNextToken();
       if(token.image.equals("{"))
       {
         temp_brace_num = temp_brace_num + 1;
         brace_num_global = brace_num_global + 1;
       }
       while(temp_brace_num > 0)
       {
          token = getNextToken();
          if(token.image.equals("{"))
          {
            temp_brace_num = temp_brace_num + 1;
            brace_num_global = brace_num_global + 1;
          }
          else if(token.image.equals("}"))
          {
            temp_brace_num = temp_brace_num -1;
            brace_num_global = brace_num_global - 1;
          }
        }
      }
      jj_consume_token(LPAREN);
      temp_value = expression();
      jj_consume_token(RPAREN);
      if(temp_value != 1)
      {
       temp_brace_num = 0;
       token = getNextToken();
       if(token.image.equals("{"))
       {
         temp_brace_num = temp_brace_num + 1;
         brace_num_global = brace_num_global + 1;
       }
       while(temp_brace_num > 0)
       {
          token = getNextToken();
          if(token.image.equals("{"))
          {
            temp_brace_num = temp_brace_num + 1;
            brace_num_global = brace_num_global + 1;
          }
          else if(token.image.equals("}"))
          {
            temp_brace_num = temp_brace_num -1;
            brace_num_global = brace_num_global - 1;
          }
       }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACE:
        statement_block();
        break;
      default:
        jj_la1[5] = jj_gen;
        ;
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
    if(temp_value == 1)
    {
      temp_brace_num = 0;
       token = getNextToken();
       if(token.image.equals("{"))
       {
         temp_brace_num = temp_brace_num + 1;
         brace_num_global = brace_num_global + 1;
       }
       while(temp_brace_num > 0)
       {
          token = getNextToken();
          if(token.image.equals("{"))
          {
            temp_brace_num = temp_brace_num + 1;
            brace_num_global = brace_num_global + 1;
          }
          else if(token.image.equals("}"))
          {
            temp_brace_num = temp_brace_num -1;
            brace_num_global = brace_num_global - 1;
          }
       }
    }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACE:
        statement_block();
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
  }

//while_statement -> "while" "(" expression ")" statement_block
  final public void while_statement() throws ParseException {
  int temp_value = 0;
  int temp_jj_gen = 0;
  int temp_brace_num = 0;
  Token temp_token;
    temp_jj_gen = jj_gen;
    temp_token = token;
    jj_consume_token(WHILE);
    jj_consume_token(LPAREN);
    temp_value = expression();
    jj_consume_token(RPAREN);
    if( temp_value != 1 )
    {
      temp_brace_num = 0;
      token = getNextToken();
      if(token.image.equals("{"))
      {
        temp_brace_num = temp_brace_num + 1;
        brace_num_global = brace_num_global + 1;
      }
      while(temp_brace_num > 0)
      {
        token = getNextToken();
        if(token.image.equals("{"))
        {
          temp_brace_num = temp_brace_num + 1;
          brace_num_global = brace_num_global + 1;
        }
        else if(token.image.equals("}"))
        {
          temp_brace_num = temp_brace_num -1;
          brace_num_global = brace_num_global - 1;
        }
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACE:
      statement_block();
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    if( temp_value == 1)
    {
      jj_gen = temp_jj_gen;
      token = temp_token;
    }
  }

//return_statement -> "return" expression;
  final public void return_statement() throws ParseException {
  int temp_value = 0;
    jj_consume_token(RETURN);
    temp_value = expression();
    jj_consume_token(SEMICOLON);
    this.Result = temp_value;
    if(brace_num_global == 1)
    {
      token = getNextToken();
      while(!token.image.equals("END"))
      {
        token = getNextToken();
        if(token.image.equals("{"))
        {
          brace_num_global = brace_num_global + 1;
        }
        else if(token.image.equals("}"))
        {
          brace_num_global = brace_num_global - 1;
        }
      }
    }
  }

//expression -> logic_expression | <RANDOM> "(" <CONSTANT> ")" | ";"
  final public int expression() throws ParseException {
  int temp_value = 0;
  int value = 0;
  Token temp_token;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CURRENT:
    case ENERMY:
    case LPAREN:
    case ID:
    case CONSTANT:
      value = logic_expression();
    {if (true) return value;}
      break;
    case RANDOM:
      jj_consume_token(RANDOM);
      jj_consume_token(LPAREN);
      temp_token = jj_consume_token(CONSTANT);
      jj_consume_token(RPAREN);
    temp_value = Integer.parseInt(temp_token.image);
    value = random.nextInt(temp_value);
    {if (true) return value;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//logic_expression -> relational_expression( ("||" | "&&") relational_expression)*
  final public int logic_expression() throws ParseException {
  int temp_value = 0;
  int value = 0;
    value = relation_expression();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SC_OR:
      case SC_AND:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SC_OR:
        jj_consume_token(SC_OR);
        temp_value = relation_expression();
        value = value > temp_value ? value : temp_value;
        {if (true) return value;}
        break;
      case SC_AND:
        jj_consume_token(SC_AND);
        temp_value = relation_expression();
        value = value * temp_value;
        {if (true) return value;}
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

//relational_expression -> addictive_expression( ("<" | "<=" | "==" | ">=" | ">" | "!=") addictive_expression)*
  final public int relation_expression() throws ParseException {
  int temp_value = 0;
  int value = 0;
    value = addictive_expression();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
      case LE:
      case EQ:
      case GE:
      case GT:
      case NE:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LT:
        jj_consume_token(LT);
        temp_value = addictive_expression();
        value = value < temp_value ? 1:0;
        {if (true) return value;}
        break;
      case LE:
        jj_consume_token(LE);
        temp_value = addictive_expression();
        value = value <= temp_value ? 1:0;
        {if (true) return value;}
        break;
      case EQ:
        jj_consume_token(EQ);
        temp_value = addictive_expression();
        value = value == temp_value ? 1:0;
        {if (true) return value;}
        break;
      case GE:
        jj_consume_token(GE);
        temp_value = addictive_expression();
        value = value >= temp_value ? 1:0;
        {if (true) return value;}
        break;
      case GT:
        jj_consume_token(GT);
        temp_value = addictive_expression();
        value = value > temp_value ? 1:0;
        {if (true) return value;}
        break;
      case NE:
        jj_consume_token(NE);
        temp_value = addictive_expression();
        value = value != temp_value ? 1:0;
        {if (true) return value;}
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

//addictive_expression -> multiplicative_expression( ("+" | "-") multiplicative_expression)*
  final public int addictive_expression() throws ParseException {
  int temp_value = 0;
  int value = 0;
    value = multiplicative_expression();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        temp_value = multiplicative_expression();
        value = value + temp_value;
        {if (true) return value;}
        break;
      case MINUS:
        jj_consume_token(MINUS);
        temp_value = multiplicative_expression();
        value = value - temp_value;
        {if (true) return value;}
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

//multiplicative_expression -> factor( ("*" | "/") factor)*
  final public int multiplicative_expression() throws ParseException {
  int temp_value = 0;
  int value = 0;
    value = factor();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULTIPLY:
      case DIVIDE:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULTIPLY:
        jj_consume_token(MULTIPLY);
        temp_value = factor();
        value = value * temp_value;
        {if (true) return value;}
        break;
      case DIVIDE:
        jj_consume_token(DIVIDE);
        temp_value = factor();
        value = value / temp_value;
        {if (true) return value;}
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

//factor -> "(" expression ")" | <ID> | <ID> "[" <CONSTANT> "]" | <CONSTANT>
  final public int factor() throws ParseException {
  int temp_value = 0;
  Token temp_token;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
      temp_value = expression();
      jj_consume_token(RPAREN);
    {if (true) return temp_value;}
      break;
    case CURRENT:
      jj_consume_token(CURRENT);
    {if (true) return Current;}
      break;
    case ENERMY:
      jj_consume_token(ENERMY);
      jj_consume_token(LBRACKET);
      temp_value = expression();
      jj_consume_token(RBRACKET);
    {if (true) return externData.Enermy_ArrayList.get(temp_value);}
      break;
    case ID:
      temp_token = jj_consume_token(ID);
    {if (true) return externData.ID_HashMap.get(temp_token.image);}
      break;
    case CONSTANT:
      temp_token = jj_consume_token(CONSTANT);
    temp_value = Integer.parseInt(temp_token.image);
    {if (true) return temp_value;}
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public YousBitWarParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xcc00,0xcc00,0x0,0x0,0x1000,0x0,0x0,0x2000,0x0,0x1c0000,0x60000000,0x60000000,0x1f800000,0x1f800000,0x80000000,0x80000000,0x0,0x0,0x180000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x1020,0x1020,0x400,0x20,0x0,0x20,0x20,0x0,0x20,0x3008,0x0,0x0,0x0,0x0,0x1,0x1,0x6,0x6,0x3008,};
   }

  /** Constructor with InputStream. */
  public YousBitWarParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public YousBitWarParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new YousBitWarParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public YousBitWarParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new YousBitWarParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public YousBitWarParser(YousBitWarParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(YousBitWarParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[48];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 48; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
