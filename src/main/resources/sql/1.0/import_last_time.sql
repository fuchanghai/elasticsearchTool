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

 Date: 31/07/2019 15:14:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for import_last_time
-- ----------------------------
DROP TABLE IF EXISTS `import_last_time`;
CREATE TABLE `import_last_time`  (
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `import_time` bigint(32) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT 0,
  `shut_down` int(1) NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of import_last_time
-- ----------------------------
INSERT INTO `import_last_time` VALUES ('l1_ship_voyage_classa_last', 1564557246113, 0, 0);
INSERT INTO `import_last_time` VALUES ('l1_ship_voyage_classunknown_last', 1564557246106, 0, 0);
INSERT INTO `import_last_time` VALUES ('l1_ship_voyage_classunknown_history', 1564557246096, 0, 0);
INSERT INTO `import_last_time` VALUES ('l0_clean_ais_dynamic_unknown', 1564482483710, 1, 0);
INSERT INTO `import_last_time` VALUES ('l1_ship_voyage_classa_history', 1564557246088, 0, 0);
INSERT INTO `import_last_time` VALUES ('l0_clean_ais_dynamic_b', 1564557246065, 0, 0);
INSERT INTO `import_last_time` VALUES ('l0_clean_ais_dynamic_a', 1564557246074, 0, 0);
INSERT INTO `import_last_time` VALUES ('wpi_ports', 1564557246118, 0, 0);
INSERT INTO `import_last_time` VALUES ('ship_archive', 1564557246082, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
