/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.10.240
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 192.168.10.240:33306
 Source Schema         : dataware

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 31/07/2019 15:35:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for specialfield
-- ----------------------------
DROP TABLE IF EXISTS `specialfield`;
CREATE TABLE `specialfield`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段',
  `codefield` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码中对应的字段',
  `tableName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经纬 区分字段',
  `pointNum` int(11) NULL DEFAULT NULL COMMENT '归属于哪一个点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of specialfield
-- ----------------------------
INSERT INTO `specialfield` VALUES (1, 'geo_shape', 'Latitude', 'latitude', 'wpi_ports', 'Lat', 1);
INSERT INTO `specialfield` VALUES (2, 'geo_shape', 'Longitude', 'longitude', 'wpi_ports', 'lon', 1);
INSERT INTO `specialfield` VALUES (3, 'geo_shape', 'Latitude', 'latitude', 'l0_clean_ais_dynamic_unknown', 'lat', 1);
INSERT INTO `specialfield` VALUES (4, 'geo_shape', 'Longitude', 'longitude', 'l0_clean_ais_dynamic_unknown', 'lon', 1);
INSERT INTO `specialfield` VALUES (5, 'geo_shape', 'Latitude', 'latitude', 'l0_clean_ais_dynamic_a', 'lat', 1);
INSERT INTO `specialfield` VALUES (6, 'geo_shape', 'Longitude', 'longitude', 'l0_clean_ais_dynamic_a', 'lon', 1);
INSERT INTO `specialfield` VALUES (7, 'geo_shape', 'Latitude', 'latitude', 'l0_clean_ais_dynamic_b', 'lat', 1);
INSERT INTO `specialfield` VALUES (8, 'geo_shape', 'Longitude', 'longitude', 'l0_clean_ais_dynamic_b', 'lon', 1);

SET FOREIGN_KEY_CHECKS = 1;
