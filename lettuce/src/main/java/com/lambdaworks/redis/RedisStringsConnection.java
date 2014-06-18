package com.lambdaworks.redis;

import java.util.List;
import java.util.Map;

import com.lambdaworks.redis.output.ValueStreamingChannel;
import com.lambdaworks.redis.protocol.SetArgs;

/**
 * Synchronous executed commands for Strings.
 * 
 * @param <K> Key type.
 * @param <V> Value type.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 17.05.14 21:12
 */
public interface RedisStringsConnection<K, V> {
    /**
     * Append a value to a key.
     * 
     * @param key the key
     * @param value the value
     * @return Long integer-reply the length of the string after the append operation.
     */
    Long append(K key, V value);

    /**
     * Count set bits in a string.
     * 
     * @return Long integer-reply The number of bits set to 1.
     */
    Long bitcount(K key);

    /**
     * Count set bits in a string.
     * 
     * @return Long integer-reply The number of bits set to 1.
     */
    Long bitcount(K key, long start, long end);

    /**
     * Find first bit set or clear in a string.
     * 
     * @return Long integer-reply The command returns the position of the first bit set to 1 or 0 according to the request.
     * 
     *         If we look for set bits (the bit argument is 1) and the string is empty or composed of just zero bytes, -1 is
     *         returned.
     * 
     *         If we look for clear bits (the bit argument is 0) and the string only contains bit set to 1, the function returns
     *         the first bit not part of the string on the right. So if the string is tree bytes set to the value 0xff the
     *         command `BITPOS key 0` will return 24, since up to bit 23 all the bits are 1.
     * 
     *         Basically the function consider the right of the string as padded with zeros if you look for clear bits and
     *         specify no range or the _start_ argument **only**.
     * 
     *         However this behavior changes if you are looking for clear bits and specify a range with both __start__ and
     *         __end__. If no clear bit is found in the specified range, the function returns -1 as the user specified a clear
     *         range and there are no 0 bits in that range.
     */
    Long bitpos(K key, boolean state);

    /**
     * Find first bit set or clear in a string.
     * 
     * @param key the key
     * @param state the bit type: long
     * @param start the start type: long
     * @param end the end type: long
     * @return Long integer-reply The command returns the position of the first bit set to 1 or 0 according to the request.
     * 
     *         If we look for set bits (the bit argument is 1) and the string is empty or composed of just zero bytes, -1 is
     *         returned.
     * 
     *         If we look for clear bits (the bit argument is 0) and the string only contains bit set to 1, the function returns
     *         the first bit not part of the string on the right. So if the string is tree bytes set to the value 0xff the
     *         command `BITPOS key 0` will return 24, since up to bit 23 all the bits are 1.
     * 
     *         Basically the function consider the right of the string as padded with zeros if you look for clear bits and
     *         specify no range or the _start_ argument **only**.
     * 
     *         However this behavior changes if you are looking for clear bits and specify a range with both __start__ and
     *         __end__. If no clear bit is found in the specified range, the function returns -1 as the user specified a clear
     *         range and there are no 0 bits in that range.
     */
    Long bitpos(K key, boolean state, long start, long end);

    Long bitopAnd(K destination, K... keys);

    Long bitopNot(K destination, K source);

    Long bitopOr(K destination, K... keys);

    Long bitopXor(K destination, K... keys);

    /**
     * Decrement the integer value of a key by one.
     * 
     * @param key the key
     * @return Long integer-reply the value of `key` after the decrement
     */
    Long decr(K key);

    /**
     * Decrement the integer value of a key by the given number.
     * 
     * @param key the key
     * @param amount the decrement type: long
     * @return Long integer-reply the value of `key` after the decrement
     */
    Long decrby(K key, long amount);

    /**
     * Get the value of a key.
     * 
     * @param key the key
     * @return V bulk-string-reply the value of `key`, or `nil` when `key` does not exist.
     */
    V get(K key);

    /**
     * Returns the bit value at offset in the string value stored at key.
     * 
     * @param key the key
     * @param offset the offset type: long
     * @return Long integer-reply the bit value stored at _offset_.
     */
    Long getbit(K key, long offset);

    /**
     * Get a substring of the string stored at a key.
     * 
     * @param key the key
     * @param start the start type: long
     * @param end the end type: long
     * @return V bulk-string-reply
     */
    V getrange(K key, long start, long end);

    /**
     * Set the string value of a key and return its old value.
     * 
     * @param key the key
     * @param value the value
     * @return V bulk-string-reply the old value stored at `key`, or `nil` when `key` did not exist.
     */
    V getset(K key, V value);

    /**
     * Increment the integer value of a key by one.
     * 
     * @param key the key
     * @return Long integer-reply the value of `key` after the increment
     */
    Long incr(K key);

    /**
     * Increment the integer value of a key by the given amount.
     * 
     * @param key the key
     * @param amount the increment type: long
     * @return Long integer-reply the value of `key` after the increment
     */
    Long incrby(K key, long amount);

    /**
     * Increment the float value of a key by the given amount.
     * 
     * @param key the key
     * @param amount the increment type: double
     * @return Double bulk-string-reply the value of `key` after the increment.
     */
    Double incrbyfloat(K key, double amount);

    /**
     * Get the values of all the given keys.
     * 
     * @param keys the key
     * @return List<V> array-reply list of values at the specified keys.
     */
    List<V> mget(K... keys);

    /**
     * Get the values of all the given keys.
     * 
     * @return Long array-reply list of values at the specified keys.
     */
    Long mget(ValueStreamingChannel<V> channel, K... keys);

    /**
     * Set multiple keys to multiple values.
     * 
     * @param map the null
     * @return String simple-string-reply always `OK` since `MSET` can't fail.
     */
    String mset(Map<K, V> map);

    /**
     * Set multiple keys to multiple values, only if none of the keys exist.
     * 
     * @param map the null
     * @return Boolean integer-reply specifically:
     * 
     *         `1` if the all the keys were set. `0` if no key was set (at least one key already existed).
     */
    Boolean msetnx(Map<K, V> map);

    /**
     * Set the string value of a key.
     * 
     * @return String simple-string-reply `OK` if `SET` was executed correctly.
     */
    String set(K key, V value);

    /**
     * Set the string value of a key.
     * 
     * @return V simple-string-reply `OK` if `SET` was executed correctly.
     */
    V set(K key, V value, SetArgs setArgs);

    /**
     * Sets or clears the bit at offset in the string value stored at key.
     * 
     * @param key the key
     * @param offset the offset type: long
     * @param value the value type: string
     * @return Long integer-reply the original bit value stored at _offset_.
     */
    Long setbit(K key, long offset, int value);

    /**
     * Set the value and expiration of a key.
     * 
     * @param key the key
     * @param seconds the seconds type: long
     * @param value the value
     * @return String simple-string-reply
     */
    String setex(K key, long seconds, V value);

    /**
     * Set the value and expiration in milliseconds of a key.
     * 
     * @param key the key
     * @param milliseconds the milliseconds type: long
     * @param value the value
     */
    String psetex(K key, long milliseconds, V value);

    /**
     * Set the value of a key, only if the key does not exist.
     * 
     * @param key the key
     * @param value the value
     * @return Boolean integer-reply specifically:
     * 
     *         `1` if the key was set `0` if the key was not set
     */
    Boolean setnx(K key, V value);

    /**
     * Overwrite part of a string at key starting at the specified offset.
     * 
     * @param key the key
     * @param offset the offset type: long
     * @param value the value
     * @return Long integer-reply the length of the string after it was modified by the command.
     */
    Long setrange(K key, long offset, V value);

    /**
     * Get the length of the value stored in a key.
     * 
     * @param key the key
     * @return Long integer-reply the length of the string at `key`, or `0` when `key` does not exist.
     */
    Long strlen(K key);
}