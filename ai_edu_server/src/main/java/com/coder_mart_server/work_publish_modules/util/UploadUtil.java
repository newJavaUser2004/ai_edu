package com.coder_mart_server.work_publish_modules.util;

import com.coder_mart_server.public_modules.constant.ResponseConstant;
import com.coder_mart_server.public_modules.helppers.UniqueIdHelper;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 上传文件的工具类
 */

@Slf4j
public class UploadUtil {

    public static String upload(MultipartFile file) {
        if (file == null) {
            return ResponseConstant.ErrorMessage.UPLOAD_FILE_IS_EMPTY;
        }

        //获取文件名后缀
        String originName = file.getOriginalFilename();
        int dot = originName.lastIndexOf(".");
        if (dot == -1) {
            return ResponseConstant.ErrorMessage.UPLOAD_FILE_HAS_NO_EXTENSION;
        }
        String tail = originName.substring(dot);

        String newFilename = UUID.randomUUID() + tail;

        //保存文件到本地
        Path rootDir = Paths.get("E:\\Idea\\Idea_project\\ai_edu\\ai_edu_server\\src\\main\\java\\com\\coder_mart_server\\filesStore");

        //文件的父目录
        Path realDir = rootDir.resolve(LocalDate.now().toString());

        //文件路径
        Path realFile = realDir.resolve(newFilename);

        try {

            //创建目录
            Files.createDirectories(realDir);
            //写盘
            file.transferTo(realFile);

        } catch (IOException e) {
            return "上传失败" + e.getMessage();
        }

        String relativePath = LocalDate.now() + "/" + newFilename;
        String url = "http://localhost:8080/files/" + relativePath;

        return url;
    }


}

//    private static final int DEFAULT_CHUNK_SIZE = 3*1024*1024;
//    private static final String REDIS_KEY_PREFIX = "chunks:";
//    private static final String MERGE_LOCK_PREFIX = "merge_lock:";
//
//    public static String quickUpload(String md5,long size,Path storageDir) throws IOException {
//
//        Path folder = storageDir.resolve("merged").resolve(md5.substring(0,2));
//        Path target = folder.resolve(md5 + ".data");
//        if (Files.exists(target)&&Files.size(target)==size){
//            return "/download/" + md5;
//        }
//        return null;
//    }
//
//    public static long chunkCount(long size){
//        return (size+DEFAULT_CHUNK_SIZE-1)/DEFAULT_CHUNK_SIZE;
//    }
//
//    public static void saveChunk(String uploadId, int chunk, byte[] data, Path tmpDir,
//                                 StringRedisTemplate redis) throws IOException {
//
//        Path chunkFile = tmpDir.resolve(uploadId).resolve(String.valueOf(chunk));
//
//        Files.createDirectories(chunkFile.getParent());
//        Files.write(chunkFile,data, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
//        redis.opsForSet().add(REDIS_KEY_PREFIX + uploadId,String.valueOf(chunk));
//
//    }
//
//    public static Set<Integer> uploadedChunks(String uploadId,StringRedisTemplate redis){
//        Set<String> set = redis.opsForSet().members(REDIS_KEY_PREFIX +uploadId);
//        if (set == null || set.isEmpty()){
//            return Set.of();
//        }
//        return set.stream().map(Integer ::valueOf).collect(Collectors.toSet());
//    }
//
//    public static String mergeChunks(String uploadId,String md5,long size,
//                                     String ext,Path tmpDir,Path storageDir,
//                                     StringRedisTemplate redis) throws IOException {
//        String lockKey = MERGE_LOCK_PREFIX + uploadId;
//        Boolean locked = redis.opsForValue().setIfAbsent(lockKey,"1", Duration.ofMinutes(5));
//
//        if (!Boolean.TRUE.equals(locked))
//            throw new IOException();
//
//        try {
//
//            Set<Integer> set = uploadedChunks(uploadId,redis);
//            if (set.isEmpty()) throw new IOException();
//            List<Integer> chunks = set.stream().sorted().toList();
//            Path merged = tmpDir.resolve(uploadId + ".merged");
//
//            try(OutputStream outputStream = Files.newOutputStream(merged,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING)) {
//                for (Integer idx : chunks){
//                    Path slice = tmpDir.resolve(uploadId).resolve(String.valueOf(idx));
//                    try (InputStream in = Files.newInputStream(slice)) {
//                        in.transferTo(outputStream);  // 零拷贝
//                    }
//                }
//
//                if ((Files.size(merged)!=size)){
//                    Files.deleteIfExists(merged);
//                    throw new IOException();
//                }
//
//                Path folder = storageDir.resolve("merged").resolve(md5.substring(0,2));
//                Files.createDirectories(folder);
//                Path target = folder.resolve(md5 + ".data");
//                Files.move(merged,target, StandardCopyOption.REPLACE_EXISTING);
//
//                Files.walk(tmpDir.resolve(uploadId)).sorted(Comparator.reverseOrder()).forEach(p->{
//                    try {
//                        Files.deleteIfExists(p);
//                    }catch (IOException ignore){}
//                });
//                redis.delete(REDIS_KEY_PREFIX+uploadId);
//                redis.delete(lockKey);
//                return "/download/" + md5;
//            }
//        }
//        catch (Exception e){
//            redis.delete(lockKey);
//            throw e;
//        }
//
//
//
//    }

