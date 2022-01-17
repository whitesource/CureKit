package io.whitesource.cure;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.owasp.encoder.Encode;

/**
 * Remediation Solver static class written by WhiteSource with the community ❤. Here you can find
 * wrapper functions to secure unsafe operations in your code.
 */
public class Encoder {

  /**
   * Encodes any non alpha numeric character with respect to the type of the operating system.
   *
   * @param param An argument or part of an argument for the operating systems command.
   * @return Encoded parameter.
   */
  public static String forOsCommand(Object param) {
    if (param == null) {
      return null;
    }
    return forOsCommand(param, new char[] {});
  }

  /**
   * Encodes any non alpha numeric character that is not part of charsToIgnore. Encoding function
   * depends on the operating system type.
   *
   * @param param An argument or part of an argument for the operating systems command.
   * @param charsToIgnore Array of characters to not encode.
   * @return Encoded parameter.
   */
  public static String forOsCommand(Object param, char[] charsToIgnore) {
    if (param == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    for (char c : formatToString(param).toCharArray()) {
      sb.append(encodeCharacterForOsCommand(c, charsToIgnore));
    }
    return sb.toString();
  }

  /**
   * Encoding content for logs.
   *
   * @param contents arrays {@link Object} contains all the contents.
   * @return encoded log content.
   */
  public static String[] forLogContent(Object[] contents) {
    if (contents == null) {
      return null;
    }
    List<String> results = new ArrayList<>();

    for (Object content : contents) {
      results.add(forLogContent(content));
    }
    return results.toArray(new String[results.size()]);
  }

  /**
   * Encoding content for logs.
   *
   * @param content {@link Object} contains the content.
   * @return encoded log content.
   */
  public static String forLogContent(Object content) {
    if (content == null) {
      return null;
    }
    return formatToString(content)
            .replaceAll("[\n|\r|\t]", "_")
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;")
            .replaceAll("[$]", "&dollar;");
  }

  /**
   * Encoding content for logs.
   *
   * @param contents arrays {@link Object} contains all the contents.
   * @return encoded log content.
   */
  public static <T extends Collection<String>> T forLogContent(Collection<?> contents) {
    if (contents == null) {
      return null;
    }
    Collection<String> results = new HashSet<>();

    for (Object content : contents) {
      results.add(forLogContent(content));
    }
    if (contents instanceof Set) {
      return (T) new HashSet<>(results);
    } else if (contents instanceof List) {
      return (T) new ArrayList<>(results);
    }

    return (T) results;
  }

  /**
   * Encoding content to prevent crlf injection by deleting new line commands.
   *
   * @param content contains the content to be sanitized.
   * @return encoded Html content.
   */
  public static String forCrlf(Object content) {
    if (content == null) {
      return null;
    }
    return StringUtils.replaceEach(
            formatToString(content),
            new String[] {"\n", "\\n", "\r", "\\r", "%0d", "%0D", "%0a", "%0A", "\025"},
            new String[] {"", "", "", "", "", "", "", "", ""});
  }

  /**
   * Encoding content to prevent crlf injection by deleting new line commands.
   *
   * @param contents contains the content to be sanitized.
   * @return encoded Html content.
   */
  public static String[] forCrlf(Object[] contents) {
    if (contents == null) {
      return null;
    }
    List<String> results = new ArrayList<>();

    for (Object content : contents) {
      results.add(forCrlf(content));
    }
    return results.toArray(new String[results.size()]);
  }

  /**
   * Encoding content to prevent crlf injection by deleting new line commands.
   *
   * @param contents contains the content to be sanitized.
   * @return encoded Html content.
   */
  public static <T extends Collection<String>> T forCrlf(Collection<?> contents) {
    if (contents == null) {
      return null;
    }
    Collection<String> results = new HashSet<>();

    for (Object content : contents) {
      results.add(forCrlf(content));
    }
    if (contents instanceof Set) {
      return (T) new HashSet<>(results);
    } else if (contents instanceof List) {
      return (T) new ArrayList<>(results);
    }

    return (T) results;
  }

  /**
   * This method encodes for JavaScript strings contained within HTML script blocks.
   *
   * @param content {@link Object} contains the content.
   * @return encoded JavaScript block.
   */
  public static String forJavaScriptBlockXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forJavaScriptBlock(formatToString(content));
  }

  /**
   * This method encodes for HTML text content. It does not escape quotation characters and is thus
   * unsafe for use with HTML attributes. Use either forHtml or forHtmlAttribute for those methods.
   *
   * @param content {@link Object} contains the content.
   * @return encoded Html content.
   */
  public static String forHtmlContentXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forHtmlContent(formatToString(content));
  }

  /**
   * This method encodes for HTML text attributes.
   *
   * @param content {@link Object} contains the content.
   * @return encoded Html Attribute.
   */
  public static String forHtmlAttributeXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forHtmlAttribute(formatToString(content));
  }

  /**
   * Encodes for a JavaScript string. It is safe for use in HTML script attributes (such as
   * onclick), script blocks, JSON files, and JavaScript source. The caller MUST provide the
   * surrounding quotation characters for the string. Since this performs additional encoding so it
   * can work in all of the JavaScript contexts listed, it may be slightly less efficient than using
   * one of the methods targetted to a specific JavaScript context (forJavaScriptAttribute(String),
   * forJavaScriptBlock(java.lang.String), forJavaScriptSource(java.lang.String)). Unless you are
   * interested in saving a few bytes of output or are writing a framework on top of this library,
   * it is recommend that you use this method over the others.
   *
   * @param content {@link Object} contains the content.
   * @return encoded JavaScript string.
   */
  public static String forJavaScriptXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forJavaScript(formatToString(content));
  }

  /**
   * Encodes for CSS strings. The context must be surrounded by quotation characters. It is safe for
   * use in both style blocks and attributes in HTML.
   *
   * @param content {@link Object} contains the content.
   * @return encoded CSS String.
   */
  public static String forCssStringXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forCssString(formatToString(content));
  }

  /**
   * Performs percent-encoding for a component of a URI, such as a query parameter name or value,
   * path or query-string. In particular this method insures that special characters in the
   * component do not get interpreted as part of another component.
   *
   * @param content {@link Object} contains the content.
   * @return encoded Uri component.
   */
  public static String forUriComponentXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forUriComponent(formatToString(content));
  }

  /**
   * Encodes for CSS URL contexts. The context must be surrounded by "url(" and ")". It is safe for
   * use in both style blocks and attributes in HTML. Note: this does not do any checking on the
   * quality or safety of the URL itself. The caller should insure that the URL is safe for
   * embedding (e.g. input validation) by other means.
   *
   * @param content {@link Object} contains the content.
   * @return encoded CSS url.
   */
  public static String forCssUrlXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forCssUrl(formatToString(content));
  }

  /**
   * Encodes for unquoted HTML attribute values. forHtml(String) or forHtmlAttribute(String) should
   * usually be preferred over this method as quoted attributes are XHTML compliant. When using this
   * method, the caller is not required to provide quotes around the attribute (since it is encoded
   * for such context). The caller should make sure that the attribute value does not abut unsafe
   * characters--and thus should usually err on the side of including a space character after the
   * value. Use of this method is discouraged as quoted attributes are generally more compatible and
   * safer. Also note, that no attempt has been made to optimize this encoding, though it is still
   * probably faster than other encoding libraries.
   *
   * @param content {@link Object} contains the content.
   * @return encoded Html unquoted Attribute.
   */
  public static String forHtmlUnquotedAttributeXss(Object content) {
    if (content == null) {
      return null;
    }
    return Encode.forHtmlUnquotedAttribute(formatToString(content));
  }

  /**
   * This method encodes for JavaScript strings contained within HTML script attributes (such as
   * onclick). It is NOT safe for use in script blocks. The caller MUST provide the surrounding
   * quotation characters. This method performs the same encode as forJavaScript(String) with the
   * exception that / is not escaped.
   *
   * @param content {@link Object} contains the content.
   * @return encoded JavaScript attribute.
   */
  public static String forJavaScriptAttributeXss(String content) {
    if (content == null) {
      return null;
    }
    return Encode.forJavaScriptAttribute(formatToString(content));
  }

  private static String encodeCharacterForOsCommand(char charToEncode, char[] charsToIgnore) {
    if (new String(charsToIgnore).indexOf(charToEncode) != -1 || isAlphaNumeric(charToEncode)) {
      return "" + charToEncode;
    }

    if (SystemUtils.IS_OS_WINDOWS) {
      return "^" + charToEncode;
    } else if (SystemUtils.IS_OS_UNIX) {
      return "\\" + charToEncode;
    } else {
      throw new RuntimeException("Unknown operation system type");
    }
  }

  private static boolean isAlphaNumeric(char charToEncode) {
    return !((charToEncode < '0' || charToEncode > '9')
            && (charToEncode < 'A' || charToEncode > 'Z')
            && (charToEncode < 'a' || charToEncode > 'z'));
  }

  private static String formatToString(Object content) {
    if (content instanceof char[]) {
      return new String((char[]) content);
    } else if (content instanceof String) {
      return (String) content;
    } else {
      return content.toString();
    }
  }
}
